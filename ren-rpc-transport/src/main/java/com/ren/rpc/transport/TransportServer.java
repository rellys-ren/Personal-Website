package com.ren.rpc.transport;

/**
 * Created by RL on 2020/04/19 20:38
 * Description:
 * 1、启动并监听接口
 * 2、接收请求
 * 3、关闭监听
 */
public interface TransportServer {
    void init(int port,RequestHandler handler);
    void start();
    void stop();
}
