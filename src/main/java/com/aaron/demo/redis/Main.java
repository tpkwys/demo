package com.aaron.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-28
 **/
public class Main {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("122.152.195.223",7006);
        //
        jedis.auth("rootroot");
        System.out.println(jedis.get("key3"));

    }
}
