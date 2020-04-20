package com.ren.rpc;

import lombok.Data;

/**
 * Created by RL on 2020/04/19 19:24
 * Description: RPC的返回
 */
@Data
public class RpcResponse {
    /**
     * 返回编码 0-成功，非0-失败
     */
    private int code = 0;
    /**
     * 具体的错误信息
     */
    private String message = "ok";
    /**
     * 返回的数据
     */
    private Object data;
}
