package com.ren.rpc.codec;

/**
 * Created by RL on 2020/04/19 20:04
 * Description: 反序列化
 */
public interface Decoder {
    public <T> T decode(byte[] bytes,Class<T> clazz);
}
