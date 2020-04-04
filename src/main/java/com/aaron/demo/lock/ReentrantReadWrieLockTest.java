package com.aaron.demo.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: demo
 * @description: 读写锁测试
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class ReentrantReadWrieLockTest {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Cache.put("key",Thread.currentThread().getName());
                }
            },"tW"+i).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Cache.get("key"));
                }
            },"tR"+i).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Cache.clear();
                }
            },"tC"+i).start();
        }

    }

}
class Cache{
    static Map<String,Object>map=new HashMap<>();
    static ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    static Lock r=rwl.readLock();//读锁
    static Lock w=rwl.writeLock();//写锁

    public static final  Object get(String key){
        r.lock();
        try{
            System.out.println("get "+Thread.currentThread().getName());
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    public static final Object put(String key,Object value){
        w.lock();
        try{
            System.out.println("put "+Thread.currentThread().getName());
           return map.put(key,value);
        }finally {
            w.unlock();
        }
    }

    public static final void clear(){
        w.lock();
        try{
            System.out.println("clear "+Thread.currentThread().getName());
            map.clear();
        }finally {
            w.unlock();
        }
    }
}
