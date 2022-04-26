package events.messaging.kafka;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.*;

@Configuration
@Profile("kafka-template")
public class KafkaConfig {

    public final static String EVENTS_CREATE_TOPIC = "create-event-notification";
    public final static String EVENTS_UPDATE_TOPIC = "update-event-notification";
    public final static String EVENTS_DELETE_TOPIC = "delete-event-notification";

    private final String bootstrapServers = "localhost:9092";

    Logger log = LoggerFactory.getLogger(KafkaConfig.class.getName());

    // create producer properties
//    @Bean
//    public Properties producerProps() {
//        Properties props = new Properties();
//        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
//        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
//        return props;
//    }
//    // create consumer properties
//    @Bean
//    public Properties consumerProps() {
//        Properties props = new Properties();
//        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
//        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
//
//        return props;
//    }

}
