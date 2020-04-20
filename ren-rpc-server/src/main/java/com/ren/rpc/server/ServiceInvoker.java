package com.ren.rpc.server;

import com.ren.rpc.RpcRequest;
import com.ren.rpc.common.utils.ReflectionUtils;

/**
 * Created by RL on 2020/04/19 21:47
 * Description: 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service,
                         RpcRequest request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
