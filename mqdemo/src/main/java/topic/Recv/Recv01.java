package topic.Recv;

import com.rabbitmq.client.*;

//主题队列模式
public class Recv01 {

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setUsername("yeb");
        factory.setVirtualHost("/yeb");
        factory.setPassword("yeb");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //获取排他队列
        String queueName = channel.queueDeclare().getQueue();
        //指定队列和交换机
        String RoutingKey = "#.message.#";
        channel.queueBind(queueName, EXCHANGE_NAME, RoutingKey);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        //b是自动回执
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}