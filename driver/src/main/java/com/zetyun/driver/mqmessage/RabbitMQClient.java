package com.zetyun.driver.mqmessage;

import com.rabbitmq.client.*;
import com.zetyun.driver.log.LogWriter;

import java.io.IOException;
import java.util.ArrayList;

public class RabbitMQClient {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Channel channel = null;
    private QueueingConsumer consumer = null;
    private RabbitMQServerConfig rmqServerConfig;
    ArrayList<String> messages;

    /**
     * 默认构造函数
     * @param config
     */
    public RabbitMQClient(RabbitMQServerConfig config){
        this.rmqServerConfig = config;
        this.messages = new ArrayList<>();
    }

    /**
     * 创建
     * @return
     */
    public boolean create(){
       try {
           //打开连接和创建频道(发送和接收共用一个名字)
           factory = new ConnectionFactory();
           LogWriter.debug(RabbitMQClient.class, "Create RabbitMQ Connection Factory success!");

           //指定RabbitMQ Server IP
           factory.setHost(rmqServerConfig.getIP());
           LogWriter.debug(RabbitMQClient.class, "Set RabbitMQ IP(" + rmqServerConfig.getIP() + ") success!");

           //设置端口
           factory.setPort(rmqServerConfig.getPORT());
           LogWriter.debug(RabbitMQClient.class, "Set RabbitMQ Port(" + String.valueOf(rmqServerConfig.getPORT()) + ") success!");

           //指定用户
           factory.setUsername(rmqServerConfig.getUSER());
           LogWriter.debug(RabbitMQClient.class, "Set RabbitMQ username(" + rmqServerConfig.getUSER() + ") success!");

           //指定密码
           factory.setPassword(rmqServerConfig.getPASSWORD());
           LogWriter.debug(
                   RabbitMQClient.class, "Set RabbitMQ password(" + rmqServerConfig.getPASSWORD() + ") success!");

           //设置路径
           factory.setVirtualHost(rmqServerConfig.getVirtualHost());
           LogWriter.debug(RabbitMQClient.class, "Set RabbitMQ virtual host(" + rmqServerConfig.getVirtualHost() + ") success!");

           return true;
       }catch (Exception ex){
           LogWriter.error(RabbitMQClient.class, "Create RabbitMQ Connection Factory fail, exception : " + ex.getMessage());
           return false;
       }
    }

    /**
     * 从指定消息队列中获取数据
     * @param queuename 消息队列名称
     * @return
     */
    public ArrayList<String> recive(String queuename){
        try{
            //创建一个连接
            connection = factory.newConnection();
            LogWriter.debug(RabbitMQClient.class, "Create Factory Connection success");

            //创建一个频道
            channel = connection.createChannel();
            LogWriter.debug(RabbitMQClient.class, "Create Connection Channel success");

            //创建队列消费者
            consumer = new QueueingConsumer(channel);
            LogWriter.debug(RabbitMQClient.class, "Create Channel Consumer success");

            // 指定接收者，第二个参数为自动应答，无需手动应答
            channel.basicConsume(queuename, true, consumer);
            LogWriter.debug(RabbitMQClient.class, "Specify the message queue name success");

            //记录起始时间
            long starttime = System.currentTimeMillis();
            LogWriter.debug(RabbitMQClient.class, "Begin receive message(" + String.valueOf(starttime) + ")");

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                messages.add(new String(delivery.getBody()));
                Thread.sleep(100);

                long endtime = System.currentTimeMillis();

                if(rmqServerConfig.getTimeout() <= endtime - starttime){
                    break;
                }
                LogWriter.debug(RabbitMQClient.class, "     receive message(" + String.valueOf(endtime) + ")");
            }

            //关闭频道
            channel.close();
            channel = null;
            LogWriter.debug(RabbitMQClient.class, "Close Connection Channel success");

            //关闭连接
            connection.close();
            connection = null;
            LogWriter.debug(RabbitMQClient.class, "Close Factory Connection success");

            return messages;
        }catch (Exception ex){
            LogWriter.error(RabbitMQClient.class, "Receive RabbitMQ message fail, exception : " + ex.getMessage());
            return null;
        }
    }

    /**
     * 发送消息
     * @param queuename 消息队列名称
     * @param message   发送的消息
     * @return
     */
    public boolean send(String queuename, String message){
        try{
            //创建一个连接
            connection = factory.newConnection();
            LogWriter.debug(RabbitMQClient.class, "Create Factory Connection success");

            //创建一个频道
            channel = connection.createChannel();
            LogWriter.debug(RabbitMQClient.class, "Create Connection Channel success");

            //指定一个队列
            channel.queueDeclare(queuename,true, false, false, null);
            LogWriter.debug(RabbitMQClient.class, "Specify a message queue(" + queuename + ") success");

            //发送一个消息
            channel.basicPublish("", queuename, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            LogWriter.debug(RabbitMQClient.class, "Send a message to queue(" + queuename + ") success");

            //关闭频道
            channel.close();
            channel = null;
            LogWriter.debug(RabbitMQClient.class, "Close Connection Channel success");

            //关闭连接
            connection.close();
            connection = null;
            LogWriter.debug(RabbitMQClient.class, "Close Factory Connection success");

            return true;
        }catch (Exception ex){
            LogWriter.error(RabbitMQClient.class, "Send Message to RabbitMQ fail, exception : " + ex.getMessage());
            return false;
        }
    }

    /**
     * 订阅消息
     * @param queuename 消息队列名称
     * @return
     */
    public boolean subscription(String queuename){
        try{
            //创建一个连接
            connection = factory.newConnection();
            LogWriter.debug(RabbitMQClient.class, "Create Factory Connection success");

            //创建一个频道
            channel = connection.createChannel();
            LogWriter.debug(RabbitMQClient.class, "Create Connection Channel success");

            //声明交换队列
            channel.exchangeDeclare("logs", "fanout");
            LogWriter.debug(RabbitMQClient.class, "Declare an exchange queue(logs) success");

            //绑定到指定的消息队列
            channel.queueBind(queuename, "logs", "");
            LogWriter.debug(RabbitMQClient.class, "Bind a message queue(" + queuename + ") success");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    messages.add(new String(body, "UTF-8"));
                    LogWriter.debug(RabbitMQClient.class, "Receive a message(" + body + ") success");
                }
            };

            // 指定接收者，第二个参数为自动应答，无需手动应答
            channel.basicConsume(queuename, true, consumer);
            LogWriter.debug(RabbitMQClient.class, "Specify the message queue name success");

            return true;
        }catch (Exception ex){
            LogWriter.error(RabbitMQClient.class, "Subscription RabbitMQ message fail, exception : " + ex.getMessage());
            return false;
        }
    }

    /**
     * 获取消息队列
     * @return
     */
    public ArrayList<String> getMessages() throws Exception{
        Thread.sleep(1000);
        return messages;
    }

    /**
     * 清空消息队列
     */
    public void restMessages(){
        this.messages.clear();
    }

    /**
     * 关闭消息连接
     * @return
     */
    public boolean close(){
        try {
            if(channel != null){
                channel.close();
                channel = null;
                LogWriter.debug(RabbitMQClient.class, "Close Connection Channel success");
            }

            if(connection != null){
                connection.close();
                connection = null;
                LogWriter.debug(RabbitMQClient.class, "Close Factory Connection success");
            }

            return true;
        }catch (Exception ex){
            channel = null;
            connection = null;
            LogWriter.error(RabbitMQClient.class, "Close RabbitMQ Client fail, exception : " + ex.getMessage());
            return false;
        }
    }
}
