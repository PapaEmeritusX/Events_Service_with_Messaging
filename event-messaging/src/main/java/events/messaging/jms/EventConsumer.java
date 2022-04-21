package events.messaging.jms;

import events.EventService;
import events.data.Event;
//import events.exceptions.EventNotFoundException;
import events.exceptions.EventNotFoundException;
import events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis-template")
public class EventConsumer {

    private final EventRepository eventRepo;

    @Autowired
    public EventConsumer(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @JmsListener(destination = "events.create.queue")
    public void createEvent(Event event) {
        eventRepo.save(event);
    }

    @JmsListener(destination = "events.update.queue")
    public void updateEvent(Event event) {
        Event eventForm = eventRepo.findById(event.getId()).orElseThrow(() -> new
                EventNotFoundException("Failed to update from ArtemisMQ: Event with id:" + event.getId() + " not found"));
        eventForm.setEventType(event.getEventType());
        eventForm.setTitle(event.getTitle());
        eventForm.setPlace(event.getPlace());
        eventForm.setSpeaker(event.getSpeaker());
        eventForm.setDateTime(event.getDateTime());
        eventRepo.save(eventForm);
    }

    @JmsListener(destination = "events.delete.queue")
    public void deleteEvent(Long id) {
        eventRepo.findById(id).orElseThrow(() -> new
                EventNotFoundException("Failed to delete from ArtemisMQ: Event with id:" + id + " not found"));
        eventRepo.deleteById(id);
    }


}
