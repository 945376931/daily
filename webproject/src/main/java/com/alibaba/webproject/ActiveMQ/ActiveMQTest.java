package com.alibaba.webproject.ActiveMQ;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import javax.jms.*;
/**
 * 消息队列中间件   RabbitMQ ，kafka,ActiveMQ
 *
 * */

public class ActiveMQTest {
    //编写消息的发送方
    @Test
    public  void test() throws JMSException{
        //创建连接工厂对象   url中的是需要本地去运行一个 ActiveMQ，例如 redis本地服务一样
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //从工厂中获取一个连接对象
        Connection connection = connectionFactory.createConnection();
        //连接MQ服务
        connection.start();
        //获得session对象   false为不需要支持事务，后面的为自动确认。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session对象创建主题 topic
        Topic topic = session.createTopic("itheimaTopic");
        //通过session对象创建消息的的发送者
        MessageProducer producer = session.createProducer(topic);
        //通过session创建消息对象
        TextMessage message = session.createTextMessage("ping");
        //发送信息
        producer.send(message);
        //关闭相关资源
        producer.close();
        session.close();
        connection.close();
    }
    //编写消息的接收方----消费者
    @Test
    public  void test2() throws  Exception{
        //创建连接工厂对象   url中的是需要本地去运行一个 ActiveMQ，例如 redis本地服务一样
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //从工厂中获取一个连接对象
        Connection connection = connectionFactory.createConnection();
        //连接MQ服务
        connection.start();
        //获得session对象   false为不需要支持事务，后面的为自动确认。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session对象创建主题 topic
        Topic topic = session.createTopic("itheimaTopic");
        //通过session对象来创建消息的消费者
        MessageConsumer consumer=session.createConsumer(topic);  //参数为接受的topic
        MessageConsumer consumer1=session.createConsumer(topic);
        //当监听到的topic中存在消息，这个方法自动执行
        consumer1.setMessageListener(message -> {
            //只要收到了topic，就会执行该方法
            TextMessage textMessage=(TextMessage) message;
            System.out.println("123");
            try {
                System.out.println("消费者接收到了消息："+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        consumer.setMessageListener(message -> {
            //只要收到了topic，就会执行该方法
            TextMessage textMessage=(TextMessage) message;
            System.out.println("123");
            try {
                System.out.println("消费者接收到了消息："+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
       //不能去关闭连接，需要一直取监听
        while(true){

        }
    }


}
