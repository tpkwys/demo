package com.aaron.demo.closeable;

import java.io.*;
import java.util.Scanner;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-06
 **/
public class Main {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        String src=scanner.nextLine();
        String dest=scanner.nextLine();
        try{
            copy(src,dest);
        }catch (Exception e){
            System.out.println("fail reason "+e.getLocalizedMessage());
        }

    }
    static void copy(String src,String dest) throws IOException{
        /**
         * 1.使用try-with-resources结构无论是否抛出异常在try-block执行完毕后都会调用资源的close方法；
         * 2.使用try-with-resources结构创建多个资源，try-block执行完毕后调用的close方法的顺序与创建资源顺序相反；
         * 3.使用try-with-resources结构，try-block块抛出异常后先执行所有资源（try的（）中声明的）的close方法然后在执行catch里面的代码然后才是finally；
         * 4.只用在try的()中声明的资源的close方法才会被调用，并且当对象销毁的时候close不会被再次调用；
         * 5.使用try-with-resources结构无须显式调用资源释放，编程效率高，代码更简洁。
         */
        try(InputStream in=new FileInputStream(src); OutputStream out=new FileOutputStream(dest)){
            byte[]buf=new byte[1024];
            int n;
            while((n=in.read(buf))>=0){
                out.write(buf,0,n);
            }
        }catch (Exception e){
            System.out.println("catch block:+"+e.getLocalizedMessage());
        }finally {
            System.out.println("finally block");
        }
    }
}
