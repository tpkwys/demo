package com.aaron.demo.aop;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class RoleInterceptor implements Interceptor {
    @Override
    public void before(Object object) {
        System.out.println("before");
    }

    @Override
    public void after(Object object) {
        System.out.println("after");
    }

    @Override
    public void afterReturining(Object object) {
        System.out.println("afterReturining");
    }

    @Override
    public void afterThrowing(Object object) {
        System.out.println("afterThrowing");
    }
}
