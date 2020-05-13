package com.aaron.demo.aop;

/**
 * @program: demo
 * @description: 拦截器接口
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public interface Interceptor {
    void before(Object object);
    void after(Object object);
    void afterReturining(Object object);
    void afterThrowing(Object object);
}
