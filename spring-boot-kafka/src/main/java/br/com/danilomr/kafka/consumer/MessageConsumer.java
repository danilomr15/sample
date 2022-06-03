package br.com.danilomr.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic-name-1}", containerFactory = "group1ListenerContainerFactory")
    public void consumeGroup1(final String message) {
        log.debug(String.format("Receiving message on topic-1 consumer; MESSAGE=[%s]", message));
        System.out.println("GOTCHA TOPIC 1!");
    }

    @KafkaListener(topics = "${kafka.topic-name-2}", containerFactory = "group2ListenerContainerFactory")
    public void consumeGroup2(final String message) {
        log.debug(String.format("Receiving message on topic-2 consumer; MESSAGE=[%s]", message));
        System.out.println("GOTCHA TOPIC 2!");
    }
}
