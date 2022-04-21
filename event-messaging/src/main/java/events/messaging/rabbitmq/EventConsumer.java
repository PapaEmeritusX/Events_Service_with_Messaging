package events.messaging.rabbitmq;

import events.data.Event;
//import events.exceptions.EventNotFoundException;
import events.exceptions.EventNotFoundException;
import events.repository.EventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Profile("rabbitmq-template")
public class EventConsumer {

    private final EventRepository eventRepo;

    @Autowired
    public EventConsumer(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @RabbitListener(queues = "events.create.queue")
    public void createEvent(Event event) {
        eventRepo.save(event);
    }

    @RabbitListener(queues = "events.update.queue")
    public void updateEvent(Event event) {
        Event eventForm = eventRepo.findById(event.getId()).orElseThrow(() -> new
                EventNotFoundException("Failed to update from RabbitMQ: Event with id:" + event.getId() + " not found"));
        eventForm.setEventType(event.getEventType());
        eventForm.setTitle(event.getTitle());
        eventForm.setPlace(event.getPlace());
        eventForm.setSpeaker(event.getSpeaker());
        eventForm.setDateTime(event.getDateTime());
        eventRepo.save(eventForm);
    }

    @RabbitListener(queues = "events.delete.queue")
    public void deleteEvent(Long id) {
        eventRepo.findById(id).orElseThrow(() -> new
                EventNotFoundException("Failed to delete from RabbitMQ: Event with id:" + id + " not found"));
        eventRepo.deleteById(id);
    }

}
