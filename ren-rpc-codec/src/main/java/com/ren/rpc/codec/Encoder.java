package com.ren.rpc.codec;

/**
 * Created by RL on 2020/04/19 20:01
 * Description: 序列化
 */
public interface Encoder {
    byte[] encode(Object o);
}
