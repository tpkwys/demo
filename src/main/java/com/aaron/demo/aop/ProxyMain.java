package com.aaron.demo.aop;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-25
 **/
public class ProxyMain {
    public static void main(String[] args) {
        RoleService roleService=new RoleServiceImpl();
        Interceptor interceptor=new RoleInterceptor();
        RoleService proxy=(RoleService) ProxyBeanUtil.getBean(roleService,interceptor);
        Role role=new Role("1","roleName","roleNote");
        proxy.printRole(role);
        System.out.println("++++++++++++++++");
        role=null;
        proxy.printRole(role);
    }
}
