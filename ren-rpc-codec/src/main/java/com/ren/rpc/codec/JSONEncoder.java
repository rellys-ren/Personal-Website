package com.ren.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * Created by RL on 2020/04/19 20:08
 * Description: 基于json的序列化实现
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object o) {
        return JSON.toJSONBytes(o);
    }
}
