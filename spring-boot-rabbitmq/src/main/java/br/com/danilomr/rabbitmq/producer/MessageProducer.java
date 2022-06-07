package br.com.danilomr.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange-name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key-name}")
    private String routingKeyName;

    public void send(final String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}
