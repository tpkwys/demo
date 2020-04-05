package com.aaron.demo.pattern.singleton;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: 懒汉式，非线程安全
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Mgr05 {
    public static Mgr05 INSTANCE;

    private Mgr05(){}

    public static Mgr05 getINSTANCE() {
        if(INSTANCE==null){//第一个线程执行到此处，挂起。第二个线程同时执行到此处，并继续往下执行。当第二个线程创建完对象，第一个线程接着往下。
            synchronized (Mgr05.class){
                try{
                    TimeUnit.MILLISECONDS.sleep(1);
                }catch (Exception e){}
                INSTANCE=new Mgr05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr05.getINSTANCE().hashCode());
            }).start();
        }
    }
}
