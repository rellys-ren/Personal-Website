package com.ren.rpc.client;

import com.ren.rpc.Peer;
import com.ren.rpc.transport.TransportClient;

import java.util.List;

/**
 * Created by RL on 2020/04/19 22:16
 * Description: 选择哪个server去连接
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport与server做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);
    void close();

}
