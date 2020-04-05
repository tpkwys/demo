package com.aaron.demo.pattern.singleton;

/**
 * @program: demo
 * @description: 静态内部类,懒加载+虚拟机保证线程安全。最完美
 * @author: tianpanke
 * @create: 2020-04-05
 **/
public class Mgr07 {
    private Mgr07(){}
    private static class Mgr07Holder{
        private static final Mgr07 INSTANCE=new Mgr07();
    }
    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr07.getInstance());
            }).start();
        }
    }
}
