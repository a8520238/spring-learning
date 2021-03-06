package direct.Send;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

//路由队列模式 所有消费者受到相同的消息
//交换机生成的队列都是排他队列
public class Send {

    private final static String EXCHANGE_NAME = "exchange_direct";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("yeb");
        factory.setVirtualHost("/yeb");
        factory.setPassword("yeb");
        factory.setPort(5672);
        try (
                //连接工厂创建连接
                //因为Connection继承了AutoClosable接口，所以能自动关闭
                Connection connection = factory.newConnection();
                //创建信道
                Channel channel = connection.createChannel()) {
            /*
            * 交换机队列
            * */
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String infoMessage = "普通信息";
            String errorMessage = "错误信息";
            String warningMessage = "警告信息";
            String infoRoutingKey = "info";
            String errorRoutingKey = "error";
            String warningRoutingKey = "warning";
            channel.basicPublish(EXCHANGE_NAME, infoRoutingKey, null, infoMessage.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, errorRoutingKey, null, errorMessage.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, warningRoutingKey, null, warningMessage.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + infoMessage + "'");
            System.out.println(" [x] Sent '" + errorMessage + "'");
            System.out.println(" [x] Sent '" + warningMessage + "'");
        }
    }
}
