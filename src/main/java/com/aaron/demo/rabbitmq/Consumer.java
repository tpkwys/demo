package com.aaron.demo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-04-27
 **/
public class Consumer {
    public static void main(String[] args) {
        //消费者不能使用try-with-resources哦
        //为什么不使用try-with-resource语句自动关闭通道和连接？
        //这样，我们只需使程序继续运行，关闭所有内容并退出！ 这将很尴尬，因为我们希望在消费者异步侦听消息到达时，该过程保持有效。
        try{
            Connection connection = ConnectionUtil.getConnection();
            Channel channel=connection.createChannel();
           channel.basicQos(1);//同一时刻mq只会发一条数据给消费者，避免不健康的消费（重量级消息导致一个消费者消费很慢）
            //声明队列
            channel.queueDeclare(Send.QUEUE_NAME,true,false,false,null);
            //定义消费者
            DefaultConsumer consumer=new DefaultConsumer(channel){
                //当消息到达时执行回调
                //We're about to tell the server to deliver us the messages from the queue.
                // Since it will push us messages asynchronously, we provide a callback in the form of an object that will buffer
                // the messages until we're ready to use them. That is what a DeliverCallback subclass does.

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg=new String(body,"UTF-8");
                    try{
                        Thread.sleep(100000);
                    }catch (Exception e){

                    }
                    System.out.println("[Receive]:"+msg);
                }
            };
            //监听队列 队列名，手动确认标识（建议false，防止消费者意外死亡导致消息丢失），消费者
            //可以通过修改autoAck标识，并意外终止该消费者，观察队列数据状况
            // true=自动确认  false=手动确认
            channel.basicConsume(Send.QUEUE_NAME,false,consumer);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
