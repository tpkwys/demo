package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description: 懒汉式，线程安全，效率低下(每次请求都要加锁)
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Mgr04 {
    public static Mgr04 INSTANCE;
    private Mgr04(){}

    public void m(){
        System.out.println("m");
    }
    public static synchronized Mgr04 getInstance(){
        if(INSTANCE==null){
            INSTANCE=new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
