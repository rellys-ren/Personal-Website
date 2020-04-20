package com.ren.rpc.client;

import com.ren.rpc.Peer;
import com.ren.rpc.codec.Decoder;
import com.ren.rpc.codec.Encoder;
import com.ren.rpc.codec.JSONDecoder;
import com.ren.rpc.codec.JSONEncoder;
import com.ren.rpc.transport.HTTPTransportClient;
import com.ren.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RL on 2020/04/19 22:31
 * Description: no description
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1",3000)
    );

}
