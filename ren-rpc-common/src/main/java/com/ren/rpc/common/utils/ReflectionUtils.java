package com.ren.rpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RL on 2020/04/19 19:27
 * Description: 反射工具类
 */
public class ReflectionUtils {
    /**
     * 根据class创建负对象
     * @param clazz 待创建对象的类
     * @param <T> 对象的类型
     * @return 创建好的对象
     */
    public static <T>T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个class的公共方法
     * @param clazz 当前类
     * @return 当前类的公共方法
     */
    public static Method[] getPublicMethods(Class clazz){
        //返回当前类所有的方法（不包含父类）
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        for(Method m : methods){
            if(Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回方法的返回结果
     */
    public static Object invoke(Object obj,
                                Method method,
                                Object... args){

        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
