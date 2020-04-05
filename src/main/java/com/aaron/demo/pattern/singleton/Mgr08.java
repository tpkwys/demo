package com.aaron.demo.pattern.singleton;

/**
 * 解决线程安全问题(JVM保证)，防止反序列化(枚举类没有构造方法)。
 */
public enum Mgr08 {
    INSTANCE;
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println(Mgr08.INSTANCE.hashCode());
        }
    }
}
