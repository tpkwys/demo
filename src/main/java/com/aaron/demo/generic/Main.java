package com.aaron.demo.generic;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-06
 **/
public class Main {
    public static void main(String[] args) {
        String s="{\n" +
                "\t\"brand\":\"hongfushi\",\n" +
                "\t  \"weight\":\"100kg\"\n" +
                "}";
        Subscrib<Apple> subscrib=new AppleSubscrib();
        Apple apple=subscrib.toObject(s);
        System.out.println(apple.toString());
    }
}
