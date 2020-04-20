package com.ren.rpc.server;

import com.ren.rpc.RpcRequest;
import com.ren.rpc.ServiceDescriptor;
import com.ren.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by RL on 2020/04/19 21:10
 * Description: 管理rpc暴露的service
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> service;
    public ServiceManager(){
        this.service = new ConcurrentHashMap<>();
    }
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method m : methods){
            ServiceInstance sis = new ServiceInstance(bean, m);
            ServiceDescriptor sd = ServiceDescriptor.from(interfaceClass,m);

            service.put(sd,sis);
            log.info("register service: {}", sd.getClazz(),sd.getMethod());
        }
    }

    public ServiceInstance lookup(RpcRequest request){
        ServiceDescriptor sdp = request.getService();
        return service.get(sdp);
    }
}
