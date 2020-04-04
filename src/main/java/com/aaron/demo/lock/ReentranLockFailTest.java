package com.aaron.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description: ReentranLock公平锁测试
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class ReentranLockFailTest implements Runnable{

    private ReentrantLock reentrantLock=new ReentrantLock(true);

    public void get(){
        System.out.println("step before get lock Thread-"+Thread.currentThread().getName());
        reentrantLock.lock();
        try{
            System.out.println("step after get lock Thread-"+Thread.currentThread().getName());
            set();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void set(){
        reentrantLock.lock();
        try{
            System.out.println("step set Thread-"+Thread.currentThread().getName());
        }finally {
            reentrantLock.unlock();
        }
    }
    @Override
    public void run() {
        System.out.println("step enter run Thread-"+Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args) {

        ReentranLockFailTest  test=new ReentranLockFailTest();
        for (int i=0;i<10;i++){
            new Thread(test,"t"+i).start();
        }
    }
    /** 获取锁的顺序和等待获取锁的顺序一直
     * step enter run Thread-t0
     * step before get lock Thread-t0
     * step after get lock Thread-t0
     * step enter run Thread-t1
     * step enter run Thread-t3
     * step before get lock Thread-t3
     * step enter run Thread-t5
     * step before get lock Thread-t5
     * step before get lock Thread-t1
     * step enter run Thread-t2
     * step before get lock Thread-t2
     * step enter run Thread-t7
     * step before get lock Thread-t7
     * step set Thread-t0
     * step enter run Thread-t8
     * step before get lock Thread-t8
     * step after get lock Thread-t3
     * step set Thread-t3
     * step after get lock Thread-t5
     * step set Thread-t5
     * step enter run Thread-t9
     * step before get lock Thread-t9
     * step enter run Thread-t6
     * step before get lock Thread-t6
     * step enter run Thread-t4
     * step before get lock Thread-t4
     * step after get lock Thread-t1
     * step set Thread-t1
     * step after get lock Thread-t2
     * step set Thread-t2
     * step after get lock Thread-t7
     * step set Thread-t7
     * step after get lock Thread-t8
     * step set Thread-t8
     * step after get lock Thread-t9
     * step set Thread-t9
     * step after get lock Thread-t6
     * step set Thread-t6
     * step after get lock Thread-t4
     * step set Thread-t4
     */
}
