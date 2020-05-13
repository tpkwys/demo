package com.aaron.demo.aop;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class RoleServiceImpl implements RoleService {

    @Override
    public void printRole(Role role) {
        System.out.println(String.format("{id=%s,name=%s,noe=%s}",role.getId(),role.getName(),role.getNote()));
    }
}
