package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description: 懒汉式,非线程安全
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class Mgr03 {
    public static Mgr03 INSTANCE;
    private Mgr03(){}
    public static Mgr03 getInstance(){
        if(INSTANCE==null){
            INSTANCE=new Mgr03();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }

}
