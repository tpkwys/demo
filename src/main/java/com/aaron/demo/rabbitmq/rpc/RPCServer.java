package com.aaron.demo.rabbitmq.rpc;

import com.aaron.demo.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @program: demo
 * @description:
 * @author: tianpanke
 * @create: 2020-05-07
 **/
public class RPCServer {
    private static final String RPC_QUEUE_NAME="fib_rpc_queue";
    private static int fib(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
    public static void main(String[] args) {
        try(Connection connection= ConnectionUtil.getConnection();
            Channel channel=connection.createChannel()){
           channel.queueDeclare(RPC_QUEUE_NAME,true,false,false,null);
           channel.queuePurge(RPC_QUEUE_NAME);
           channel.basicQos(1);
            System.out.println("[x] awating rpc requests");
            Object monitor=new Object();
            DeliverCallback deliverCallback=(consumerTag, message) -> {
                AMQP.BasicProperties replyProps=new AMQP.BasicProperties
                        .Builder()
                        .correlationId(message.getProperties().getCorrelationId())
                        .build();
                String respone="";
                try{
                    String str=new String(message.getBody(),"UTF-8");
                    int n=Integer.parseInt(str);
                    System.out.println("[.]fib("+str+")");
                    respone+=fib(n);
                }catch (RuntimeException e){
                    System.out.println(e.toString());
                }finally {
                    channel.basicPublish("",message.getProperties().getReplyTo(),replyProps,respone.getBytes("UTF-8"));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                    synchronized (monitor){
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME,false,deliverCallback,(consumerTag -> {}));
            while (true){
                synchronized (monitor){
                    try{
                        monitor.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){

        }
    }
}
