package com.aaron.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-27
 **/
public class Send {
    public static final String QUEUE_NAME="First_Queue";

    public static void main(String[] args) {
        System.out.println("hi");
        //try-with-resource
        try(Connection connection=ConnectionUtil.getConnection();
            //从连接中获取通道
            Channel channel=connection.createChannel()){
            //This queueDeclare change needs to be applied to both the producer and consumer code.
            //声明队列  队列名，队列持久化标识（防止队列服务器崩溃，重启队列丢失，不能保证消息不丢失哦，true=持久化 false不持久化;建议手动关闭mq服务测试这种场景）
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            for(int i=0;i<10;i++){
                String message="hello "+System.currentTimeMillis();
                //发送消息 交换机，队列名称，持久化消息（防止队列服务器重启，消息丢失，前提队列不能丢失哦；建议手动关闭mq服务器测试这种场景），二进制消息
                /**
                 * 将消息标记为持久性并不能完全保证不会丢失消息。 尽管它告诉RabbitMQ将消息保存到磁盘，但是RabbitMQ接受消息但尚未将其保存仍然有很短的时间。
                 * 另外，RabbitMQ不会对每条消息都执行fsync（2）－它可能只是保存到缓存中，而没有真正写入磁盘。 持久性保证并不强，但是对于我们的简单任务队列而言，
                 * 这已经绰绰有余了。 如果您需要更强有力的保证，则可以使用发布者确认。
                 */
                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
                System.out.println("[send]:"+message);
            }
        }catch (IOException| TimeoutException e){
            e.printStackTrace();
        }
    }
}
