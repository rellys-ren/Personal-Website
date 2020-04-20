package com.ren.rpc.demo;

/**
 * Created by RL on 2020/04/19 23:11
 * Description: no description
 */
public class CalServiseImpl implements CalServise {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
