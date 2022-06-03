package br.com.danilomr.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.retrytopic.DestinationTopic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-server}")
    private String bootstrapAddress;

    @Value("${kafka.group-id-1}")
    private String groupId1;

    @Value("${kafka.group-id-2}")
    private String groupId2;

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        final Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }

    @Bean
    public ConsumerFactory<String, String> group1ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getCommonProps(groupId1));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> group1ListenerContainerFactory() {
        return getContainerFactory(group1ConsumerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> group2ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getCommonProps(groupId2));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> group2ListenerContainerFactory() {
        return getContainerFactory(group2ConsumerFactory());
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> getContainerFactory(final ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    private Map<String, Object> getCommonProps(final String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
