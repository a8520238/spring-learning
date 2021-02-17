package com.send.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue() {
        return new Queue("amqp_queue");
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("amqp_exchange");
    }
    @Bean
    public Binding binding() {
       return BindingBuilder.bind(queue()).to(topicExchange()).with("*.amqp.#");
    }
}
