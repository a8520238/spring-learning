package tx.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "tx";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            //连接工厂创建连接
            //因为Connection继承了AutoClosable接口，所以能自动关闭
            connection = factory.newConnection();
            //创建信道
            channel = connection.createChannel();

            //确认模式 同步
            //channel.confirmSelect();
            //发送消息后
            //普通confirm模式
            //if (channel.waitForConfirms()) {
            //  System.out.println("消息发送成功");
            // }
            //批量confirm模式,只要有一条没确认，就会跑异常
//            channel.waitForConfirmsOrDie();
//            System.out.println("消息发送成功");


            channel.txSelect();
            /*
            * 绑定队列
            * b代表持久化
            * b1代表排他队列，只属于当前连接，别的连接看不到，不同连接的排他队列名字不能相同，连接断开时自动删除
            * b2代表自动删除
            * */

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            channel.txCommit();
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
        }
    }
}
