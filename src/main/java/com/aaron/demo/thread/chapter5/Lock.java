package com.aaron.demo.thread.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @program: demo
 * @description: 自定义显示锁接口
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public interface Lock {
    //获取锁，该方法永远阻塞，除非获取到了锁，这一点和Synchronized非常相似，但是该方法时可以被中断的
    void lock() throws InterruptedException;
    //该方法除了可以被中断外，还增加了超时功能，超时抛出异常。
    void lock(long mills) throws InterruptedException, TimeoutException;
    //释放锁
    void unlock();
    //获取被阻塞的线程队列
    List<Thread> getBlockkedThreads();
}
