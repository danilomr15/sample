package br.com.danilomr.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue-name}"})
    public void consume(@Payload final String message) {
        System.out.println("GOTCHA!");
        System.out.println(message);
    }
}
