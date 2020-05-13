package com.aaron.demo.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-12
 **/
public class InvokeAll {
    public static void main(String[] args)  throws Exception{
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
        List<Future<String>> futures=executorService.invokeAll(callables);
        for(Future<String> future:futures){
            System.out.println("future.get="+future.get());
        }
        //用完记得回收垃圾哦，Executor并不会立即关闭，但是它将不再接收新的任务，而且一旦所有线程都完成了当前任务的时候，Executor将会关闭。
        //如果想要立即
        executorService.shutdown();
    }
}
