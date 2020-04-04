package com.aaron.demo.lock;

/**
 * @program: demo
 * @description: Synchronized可重入锁验证
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class SynchronizedTest implements Runnable{

    public synchronized void get(){
        System.out.println("step2 enter get Thread-"+Thread.currentThread().getName());
        set();
        System.out.println("step4 leave get Thread-"+Thread.currentThread().getName());
    }

    public synchronized void set(){
        System.out.println("spet3 enter set Thread-"+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("step1 run Thread-"+Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args) {
        SynchronizedTest test=new SynchronizedTest();
        for (int i=0;i<10;i++){
            new Thread(test,"t"+i).start();
        }
    }
    /**
     * 某次打印结果如下，说明get方法顺利进入set方法，说明synchronized的确是可重入锁。
     * t0先进入get 方法 这时t4,t1,t3,t6,t2.等待进入，但是当t0离开时 t7却抢先进入get方法，说明
     * synchronized的确是非公平锁。
     * step1 run Thread-t0
     * step1 run Thread-t4
     * step1 run Thread-t1
     * step1 run Thread-t3
     * step1 run Thread-t6
     * step1 run Thread-t2
     * step1 run Thread-t5
     * step2 enter get Thread-t0
     * spet3 enter set Thread-t0
     * step4 leave get Thread-t0
     * step1 run Thread-t7
     * step1 run Thread-t8
     * step2 enter get Thread-t7
     * step1 run Thread-t9
     * spet3 enter set Thread-t7
     * step4 leave get Thread-t7
     * step2 enter get Thread-t5
     * spet3 enter set Thread-t5
     * step4 leave get Thread-t5
     * step2 enter get Thread-t2
     * spet3 enter set Thread-t2
     * step4 leave get Thread-t2
     * step2 enter get Thread-t6
     * spet3 enter set Thread-t6
     * step4 leave get Thread-t6
     * step2 enter get Thread-t3
     * spet3 enter set Thread-t3
     * step4 leave get Thread-t3
     * step2 enter get Thread-t4
     * spet3 enter set Thread-t4
     * step4 leave get Thread-t4
     * step2 enter get Thread-t1
     * spet3 enter set Thread-t1
     * step4 leave get Thread-t1
     * step2 enter get Thread-t9
     * spet3 enter set Thread-t9
     * step4 leave get Thread-t9
     * step2 enter get Thread-t8
     * spet3 enter set Thread-t8
     * step4 leave get Thread-t8
     */
}
