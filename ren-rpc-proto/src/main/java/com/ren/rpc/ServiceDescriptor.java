package com.ren.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by RL on 2020/04/19 19:21
 * Description: 表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;
    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sd = new ServiceDescriptor();
        sd.setClazz(clazz.getName());
        sd.setMethod(method.getName());
        sd.setReturnType(method.getReturnType().getName());

        Class[] parameters = method.getParameterTypes();
        String [] parameterTypes = new String[parameters.length];
        for(int i = 0;i <parameterTypes.length;i++){
            parameterTypes[i] = parameters[i].getName();
        }
        sd.setParameterTypes(parameterTypes);
        return sd;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        ServiceDescriptor that  = (ServiceDescriptor)obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz = " + clazz
                + "method = "+ method
                + "returnType = " + returnType
                + "parameterTypes = " + Arrays.toString(parameterTypes);
    }
}
