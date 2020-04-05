package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description:饿汉式 同Mgr01
 * @author: tianpanke
 * @create: 2020-04-04
 **/
public class Mgr02 {
    public static final Mgr02 INSTANCE;
    static {
        INSTANCE=new Mgr02();
    }
    private Mgr02(){}
    public static Mgr02 getInstance(){
        return INSTANCE;
    }
    public void m(){
        System.out.println("M");
    }

    public static void main(String[] args) {
        Mgr02 m1=Mgr02.getInstance();
        Mgr02 m2=Mgr02.getInstance();
        System.out.println(m1==m2);
    }
}
