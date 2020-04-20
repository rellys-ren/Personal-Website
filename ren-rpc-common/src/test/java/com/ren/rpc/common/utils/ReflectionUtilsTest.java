package com.ren.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by RL on 2020/04/19 19:43
 * Description: no description
 */
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        System.out.println(t);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        System.out.println("公共方法有：" + methods.length + "个，第一个名为：" + methods[0].getName());
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Object re = ReflectionUtils.invoke(new TestClass(),methods[0]);
        System.out.println(re);
    }
}
