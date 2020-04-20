package com.ren.rpc.transport;

import com.ren.rpc.Peer;

import java.io.InputStream;

/**
 * Created by RL on 2020/04/19 20:37
 * Description:
 * 1、创建连接
 * 2、发送数据，并等待响应
 * 3、完成后关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
