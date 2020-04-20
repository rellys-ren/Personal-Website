package com.ren.rpc;

import lombok.Data;

/**
 * Created by RL on 2020/04/19 19:23
 * Description: RPC的一个请求
 */
@Data
public class RpcRequest {
    private ServiceDescriptor service;
    private Object[] parameters;
}
