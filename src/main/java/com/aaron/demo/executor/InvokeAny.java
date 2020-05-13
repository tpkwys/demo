package com.aaron.demo.executor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-12
 **/
public class InvokeAny {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables=new HashSet<>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call3";
            }
        });

        String result=executorService.invokeAny(callables);
        System.out.println("result="+result);
        executorService.shutdown();
    }
}
