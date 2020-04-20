package com.ren.rpc.server;

import com.ren.rpc.RpcRequest;
import com.ren.rpc.ServiceDescriptor;
import com.ren.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by RL on 2020/04/19 21:38
 * Description: no description
 */
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init(){
        sm = new ServiceManager();
        register();
    }
    @Test
    public void register() {
        TestInterface bean = new TestBean();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,method);

        RpcRequest request = new RpcRequest();
        request.setService(sdp);

        ServiceInstance sis = sm.lookup(request);
        System.out.println(sis);
    }
}
