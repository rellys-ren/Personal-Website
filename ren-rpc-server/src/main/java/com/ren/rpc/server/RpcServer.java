package com.ren.rpc.server;

import com.ren.rpc.RpcRequest;
import com.ren.rpc.RpcResponse;
import com.ren.rpc.codec.Decoder;
import com.ren.rpc.codec.Encoder;
import com.ren.rpc.common.utils.ReflectionUtils;
import com.ren.rpc.transport.RequestHandler;
import com.ren.rpc.transport.TransportServer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by RL on 2020/04/19 21:49
 * Description: no description
 */
@Slf4j
public class RpcServer {
    //配置信息
    private RpcServerConfig config;
    //网络模块
    private TransportServer net;
    //序列化
    private Encoder encoder;
    //反序列化
    private Decoder decoder;

    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config){
        this.config = config;

        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onReRequest(InputStream recive, OutputStream repo) {
            RpcResponse resp = new RpcResponse();
            try{
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                RpcRequest request = decoder.decode(inBytes,RpcRequest.class);
                log.info("get request : {}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);
            }catch (Exception e){
                log.warn(e.getMessage() ,e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: " + e.getClass().getName() + e.getMessage());
            }finally {
                byte[] out = encoder.encode(resp);
                try {
                    repo.write(out);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };

}
