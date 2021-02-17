package work.fair.Recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

//工作队列公平 消费者
public class Recv01 {

    private final static String QUEUE_NAME = "work_fair";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setUsername("yeb");
        factory.setVirtualHost("/yeb");
        factory.setPassword("yeb");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        int prefetchCount = 1;
        //限制消费者每次只能接受一条，执行完才能接受下一条，这两行使得成为公平模式，能者多劳
        channel.basicQos(prefetchCount);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            /*
            * 手动确认，b是否确认多条
            * */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        //b是自动回执
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
}