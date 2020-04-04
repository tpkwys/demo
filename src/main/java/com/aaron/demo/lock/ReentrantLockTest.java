package com.aaron.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description: ReentranLock可重入验证
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class ReentrantLockTest implements Runnable{

    private ReentrantLock reentrantLock=new ReentrantLock();

    public void get(){
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

        ReentrantLockTest test=new ReentrantLockTest();
        for (int i=0;i<10;i++){
            new Thread(test,"t"+i).start();
        }
    }
    /**
     * 某次运行打印结果如下，观察可知 进入get方法的都顺利进入set方法，说明ReentranLock是可重入锁；
     * t0持有锁期间，t3等待获取锁，但是当t0释放锁后，t6却优先获取到锁，说明ReentranLock默认是非公平锁。
     step enter run Thread-t0
     step enter run Thread-t3
     step enter run Thread-t1
     step enter run Thread-t2
     step enter run Thread-t4
     step after get lock Thread-t0
     step enter run Thread-t5
     step set Thread-t0
     step enter run Thread-t6
     step enter run Thread-t7
     step after get lock Thread-t6
     step enter run Thread-t8
     step enter run Thread-t9
     step set Thread-t6
     step after get lock Thread-t3
     step set Thread-t3
     step after get lock Thread-t1
     step set Thread-t1
     step after get lock Thread-t2
     step set Thread-t2
     step after get lock Thread-t4
     step set Thread-t4
     step after get lock Thread-t5
     step set Thread-t5
     step after get lock Thread-t7
     step set Thread-t7
     step after get lock Thread-t8
     step set Thread-t8
     step after get lock Thread-t9
     step set Thread-t9
     */
}
