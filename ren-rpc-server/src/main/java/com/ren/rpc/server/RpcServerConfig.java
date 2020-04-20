package com.ren.rpc.server;

import com.ren.rpc.codec.Decoder;
import com.ren.rpc.codec.Encoder;
import com.ren.rpc.codec.JSONDecoder;
import com.ren.rpc.codec.JSONEncoder;
import com.ren.rpc.transport.HTTPTransportServer;
import com.ren.rpc.transport.TransportServer;
import lombok.Data;

/**
 * Created by RL on 2020/04/19 21:01
 * Description: server配置
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port  = 3000;


}
