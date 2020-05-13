package com.aaron.demo.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-13
 **/
public class RedisClusterMain {

    public static void main(String[] args) {
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        //最大连接数
        poolConfig.setMaxTotal(1);
        //最大空闲数
        poolConfig.setMaxIdle(1);
        //最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes=new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("122.152.195.223",7001));
        nodes.add(new HostAndPort("122.152.195.223",7002));
        nodes.add(new HostAndPort("122.152.195.223",7003));
        nodes.add(new HostAndPort("122.152.195.223",7004));
        nodes.add(new HostAndPort("122.152.195.223",7005));
        nodes.add(new HostAndPort("122.152.195.223",7006));
        JedisCluster jedisCluster=new JedisCluster(nodes,3000,3000,5,"rootroot",poolConfig);
        String value1=jedisCluster.get("key1");
        System.out.println("key1->"+value1);
        jedisCluster.set("key7","value7");
        System.out.println("key7->"+jedisCluster.get("key7"));
        jedisCluster.close();
    }
}