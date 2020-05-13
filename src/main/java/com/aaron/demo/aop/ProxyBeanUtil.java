package com.aaron.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: demo
 * @description: 代理
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class ProxyBeanUtil implements InvocationHandler {
    private Object object;//被代理对象
    private Interceptor interceptor;//拦截器

    public static Object getBean(Object object,Interceptor interceptor){
        //使用当前类，作为代理方法，此时被代理对象执行方法的时候，会进入当前类的invoke方法
        ProxyBeanUtil _this=new ProxyBeanUtil();
        //被代理对象
        _this.object=object;
        //拦截器
        _this.interceptor=interceptor;
        //生成代理对象，并绑定代理方法

        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),_this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj=null;
        //是否产生异常
        boolean exceptionFlag=false;
        //before
        interceptor.before(object);
        try{
            //反射原有方法
            retObj=method.invoke(object,args);
        }catch (Exception e){
            exceptionFlag=true;
        }finally {
            interceptor.after(object);
        }
        if(exceptionFlag){
            interceptor.afterThrowing(object);
        }else {
            interceptor.afterReturining(object);
        }
        return null;
    }
}
