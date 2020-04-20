package com.ren.rpc.client;

import com.ren.rpc.RpcRequest;
import com.ren.rpc.RpcResponse;
import com.ren.rpc.ServiceDescriptor;
import com.ren.rpc.codec.Decoder;
import com.ren.rpc.codec.Encoder;
import com.ren.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by RL on 2020/04/19 22:50
 * Description: 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request  =new RpcRequest();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        RpcResponse resp = invokeRemote(request);
        if(resp == null || resp.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote:" + resp);
        }
        return resp.getData();
    }

    private RpcResponse invokeRemote(RpcRequest request) {
        RpcResponse resp = null;
        TransportClient client = null;
        try{
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            byte[] bytes = IOUtils.readFully(revice, revice.available());
            resp = decoder.decode(bytes,RpcResponse.class);
        }catch (Exception e){
            log.warn(e.getMessage(),e);
            resp.setCode(1);
            resp.setMessage("RpcCilent got error" + e.getClass().getName()  + ": "+ e.getMessage());

        }finally {
            if(client != null){
                selector.release(client);
            }
        }
        return resp;
    }
}
