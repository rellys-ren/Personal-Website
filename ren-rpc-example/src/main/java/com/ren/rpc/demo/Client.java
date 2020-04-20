package com.ren.rpc.demo;

import com.ren.rpc.client.RpcClient;

/**
 * Created by RL on 2020/04/19 23:07
 * Description: no description
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalServise service = client.getProxy(CalServise.class);

        int r1 = service.add(5, 8);
        int r2 = service.minus(120, 10);

        System.out.println("加法结果：" + r1 + "\n减法结果：" + r2);
    }
}
