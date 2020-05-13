package com.aaron.demo.thread.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-25
 **/
//通过控制Boolean变量的开关，是否允许当前线程获取该锁
public class BooleanLock implements Lock{
    private Thread currentThread;
    private boolean locked;
    private final List<Thread> blockedList=new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                //暂存当前线程
                final Thread curr=Thread.currentThread();
                try {
                    blockedList.add(Thread.currentThread());
                    this.wait();
                }catch (InterruptedException e){
                    blockedList.remove(curr);
                    throw  e;
                }
            }
            blockedList.remove(Thread.currentThread());
            this.locked=true;
            this.currentThread=Thread.currentThread();
        }

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills<0){
                this.lock();
            }else{
                long remainMills=mills;
                long endMills=System.currentTimeMillis()+remainMills;
                while (locked){
                    if(remainMills<=0){
                        throw new TimeoutException(String.format("cannot get lock during %s ms",mills));
                    }
                    if(!blockedList.contains(Thread.currentThread())){
                        blockedList.add(Thread.currentThread());
                    }
                    try {
                        this.wait(remainMills);
                        remainMills = endMills - System.currentTimeMillis();
                    }catch (InterruptedException e){
                        blockedList.remove(Thread.currentThread());
                        throw  e;
                    }
                }
                //执行到此处表明，某线程已经成功拿到锁
                blockedList.remove(Thread.currentThread());
                this.locked=true;
                this.currentThread=Thread.currentThread();

            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread==Thread.currentThread()){
                this.locked=false;
                this.notifyAll();
            }
        }

    }

    @Override
    public List<Thread> getBlockkedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
