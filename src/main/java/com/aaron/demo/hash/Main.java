package com.aaron.demo.hash;

import com.aaron.demo.aop.Role;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-26
 **/
public class Main {
    public static void main(String[] args) {
        Role role1=new Role("1","role1","note1");
        Role role2=new Role("1","role1","note1");
        System.out.println(role1.equals(role2));
        System.out.println(role1.hashCode());
        System.out.println(role2.hashCode());
    }
}
