package br.com.danilomr.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProducer {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Value("${kafka.topic-name-1}")
    private String topic1;

    @Value("${kafka.topic-name-2}")
    private String topic2;

    public void produceGroup1(final String message) {
        log.debug(String.format("Sending message to topic-1 from producer; MESSAGE=[%s]", message));
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic1, message);
        kafkaProducer.send(record);
    }

    public void produceGroup2(final String message) {
        log.debug(String.format("Receiving message to topic-2 from producer; MESSAGE=[%s]", message));
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic2, message);
        kafkaProducer.send(record);
    }
}
