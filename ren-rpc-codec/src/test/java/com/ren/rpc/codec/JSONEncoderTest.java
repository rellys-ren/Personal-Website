package com.ren.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RL on 2020/04/19 20:28
 * Description: no description
 */
public class JSONEncoderTest {
    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("Robbie");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);

        Decoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(bytes, TestBean.class);
        System.out.println(decode.getName());

    }
}
