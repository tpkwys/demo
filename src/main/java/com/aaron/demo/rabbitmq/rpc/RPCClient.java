package com.aaron.demo.rabbitmq.rpc;

import com.aaron.demo.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-07
 **/
public class RPCClient  implements AutoCloseable{
    private Connection connection;
    private Channel channel;
    private String requestQueueName="fib_rpc_queue";
    public RPCClient() throws IOException, TimeoutException{
        connection= ConnectionUtil.getConnection();
        channel=connection.createChannel();
    }
    public String call(String message) throws IOException,InterruptedException{
        final String corrId= UUID.randomUUID().toString();

        String replyQueueName=channel.queueDeclare().getQueue();
        AMQP.BasicProperties properties=new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("",requestQueueName,properties,message.getBytes("UTF-8"));
        final BlockingQueue<String> response=new ArrayBlockingQueue<>(1);
        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }
    public static void main(String[] args) {
        try(RPCClient fibRpc=new RPCClient()){
            for(int i=0;i<32;i++){
                String i_str=Integer.toString(i);
                System.out.println("[x] request fib("+i_str+")");
                String respone=fibRpc.call(i_str);
                System.out.println("[x] got "+respone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
