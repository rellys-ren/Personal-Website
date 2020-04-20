package com.ren.rpc.demo;

import com.ren.rpc.server.RpcServer;
import com.ren.rpc.server.RpcServerConfig;

/**
 * Created by RL on 2020/04/19 23:07
 * Description: 还不足的地方
 * 1.安全性不够（数据用序列化传输的时候）
 * 2.服务端处理能力不够
 * 3.没有注册中心
 * 4.缺少集成的能力
 *
 */
public class Server{
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalServise.class,new CalServiseImpl());
        server.start();
    }
}
