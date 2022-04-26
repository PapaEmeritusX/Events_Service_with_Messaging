package events.messaging.kafka;

import events.EventMessaging;
import events.data.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka-template")
public class EventProducer implements EventMessaging {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public EventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createEvent(Event event) {
        kafkaTemplate.send(KafkaConfig.EVENTS_CREATE_TOPIC, event);
    }

    @Override
    public void updateEvent(Event event) {
        kafkaTemplate.send(KafkaConfig.EVENTS_UPDATE_TOPIC, event);

    }

    @Override
    public void deleteEvent(Long id) {
        kafkaTemplate.send(KafkaConfig.EVENTS_DELETE_TOPIC, id);
    }
}
