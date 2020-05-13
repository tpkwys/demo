package com.aaron.demo.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-27
 **/
public class ConnectionUtil {
    public static Connection getConnection(){
        try{
            Connection connection=null;
            //定义一个连接工厂
            ConnectionFactory factory=new ConnectionFactory();
            //设置ip
            factory.setHost("122.152.195.223");
            //端口
            factory.setPort(5672);
            //设置虚拟主机
            factory.setVirtualHost("/");
            factory.setUsername("guest");
            factory.setConnectionTimeout(10000);
            factory.setPassword("rootroot");
            connection=factory.newConnection();
            return connection;
        }catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }
}
