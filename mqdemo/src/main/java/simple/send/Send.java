package simple.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (
                //连接工厂创建连接
                //因为Connection继承了AutoClosable接口，所以能自动关闭
                Connection connection = factory.newConnection();
                //创建信道
                Channel channel = connection.createChannel()) {
            /*
            * 绑定队列
            * b代表持久化
            * b1代表排他队列，只属于当前连接，别的连接看不到，不同连接的排他队列名字不能相同，连接断开时自动删除
            * b2代表自动删除
            * */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
