package com.ren.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * Created by RL on 2020/04/19 20:24
 * Description: 基于json的反序列化实现
 */
public class JSONDecoder implements Decoder {
    public <T> T decode(byte[] bytes,Class<T> clazz){
        return JSON.parseObject(bytes,clazz);
    }
}
