package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description: 双重检验锁，依然非线程安全.看似完美(加volatile)
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Mgr06 {
    public static volatile Mgr06 INSTANCE;
    private Mgr06(){}
    public static Mgr06 getInstance(){
        if(INSTANCE==null){//xx
            synchronized (Mgr06.class){
                if(INSTANCE==null){
                    INSTANCE=new Mgr06();
                    //对象实例化步骤 1.开辟内存，并给成员变量初始化默认值 2.给成员变量真实值 3.引用指针指向内存
                    //当第一个线程进入此处，底层汇编，有可能发生指令重排。1，3，2.此时第二个线程进入xx处，得知INSTANCE不为空，结果返回个未真正实例化的对象。
                    //解决方法，加volatile
                    }
            }
        }
        return INSTANCE;
    }
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}
