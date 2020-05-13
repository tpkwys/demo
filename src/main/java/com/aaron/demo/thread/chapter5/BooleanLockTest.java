package com.aaron.demo.thread.chapter5;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class BooleanLockTest {
    //定义锁
    private final BooleanLock booleanLock=new BooleanLock();

    public void syncMethod(){
        try{
            booleanLock.lock();
            int randomInt=3;
            System.out.println(Thread.currentThread()+"get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            booleanLock.unlock();
        }
    }
    public static void main(String[] args) {
        BooleanLockTest test=new BooleanLockTest();
        IntStream.range(0,10)
                .mapToObj(i->new Thread(test::syncMethod))
                .forEach(Thread::start);
    }
}
