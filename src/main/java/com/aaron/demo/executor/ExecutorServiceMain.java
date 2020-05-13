package com.aaron.demo.executor;

import java.util.concurrent.*;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-12
 **/
public class ExecutorServiceMain {
    public static void main(String[] args) throws Exception {
        //创建单一线程的线程池
        ExecutorService executorService0=Executors.newSingleThreadExecutor();
        //创建固定数目线程的线程池
        ExecutorService executorService1= Executors.newFixedThreadPool(10);
        //创建执行计划的线程池
        ExecutorService executorService2=Executors.newScheduledThreadPool(10);
        //创建缓存线程池
        ExecutorService executorService3=Executors.newCachedThreadPool();
        for(int i=0;i<2;i++){
            //很抱歉没有办法得知runnable的执行结果
            executorService1.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("异步任务"+Thread.currentThread().getId());
                }
            });
        }

        //submit方法也要求一个Runnable实现类，但它返回一个Future对象。这个Future对象可以用来检查Runnable是否执行完毕。
        Future future=executorService1.submit(new Runnable(){
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("异步任务-submit-runnable");
            }
        });
        //returns null if the task has finished correctly
        System.out.println("return from future "+future.get());

        Future future1=executorService1.submit(new Callable() {
            @Override
            public Object call() throws Exception {

                System.out.println("listening.....");
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "a call from Taiwan";
            }
        });
        System.out.println("future.get ="+future1.get());
    }
}
