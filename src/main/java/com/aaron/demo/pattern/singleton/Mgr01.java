package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description: 懒汉模式
 * @author: tianpanke
 * @create: 2020-04-04
 **/

/**
 * 懒汉式
 * 类加载到内存中后，就实例化一个单例，JVM保证线程安全
 * 简单使用，推荐使用!
 * 唯一缺点：不管是否用到，类加载完成就实例化
 */
public class Mgr01 {

    private static final Mgr01 INSTANCE=new Mgr01();
    //构造私有化，防止外部构造
    private Mgr01(){}

    public static Mgr01 getInstance(){
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1=Mgr01.getInstance();
        Mgr01 m2=Mgr01.getInstance();
        System.out.println(m1==m2);
    }
}
