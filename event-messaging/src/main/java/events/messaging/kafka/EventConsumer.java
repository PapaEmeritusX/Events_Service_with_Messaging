package events.messaging.kafka;

import events.data.Event;
import events.exceptions.EventNotFoundException;
import events.repository.EventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Profile("kafka-template")
public class EventConsumer {

    private EventRepository eventRepo;

    @Autowired
    public EventConsumer(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @KafkaListener(topics = KafkaConfig.EVENTS_CREATE_TOPIC)
    public void createEvent(Event event) {
        eventRepo.save(event);
    }

    @KafkaListener(topics = KafkaConfig.EVENTS_UPDATE_TOPIC)
    public void updateEvent(Event event) {
        Event eventForm = eventRepo.findById(event.getId()).orElseThrow(() -> new
                EventNotFoundException("Failed to update from Kafka: Event with id:" + event.getId() + " not found"));
        eventForm.setEventType(event.getEventType());
        eventForm.setTitle(event.getTitle());
        eventForm.setPlace(event.getPlace());
        eventForm.setSpeaker(event.getSpeaker());
        eventForm.setDateTime(event.getDateTime());
        eventRepo.save(eventForm);
    }

    @KafkaListener(topics = KafkaConfig.EVENTS_DELETE_TOPIC)
    public void deleteEvent(Long id) {
        eventRepo.findById(id).orElseThrow(() -> new
                EventNotFoundException("Failed to delete from RabbitMQ: Event with id:" + id + " not found"));
        eventRepo.deleteById(id);
    }

}
