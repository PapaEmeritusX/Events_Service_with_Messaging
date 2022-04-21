package events;

import events.data.Event;
import events.repository.EventRepository;
import events.exceptions.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("DEFAULT")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepo;

    private final EventMessaging eventMessaging;

    @Autowired
    public EventServiceImpl(EventRepository eventsRepo, EventMessaging eventMessaging) {
        this.eventRepo = eventsRepo;
        this.eventMessaging = eventMessaging;
    }

    @Override
    public Event createEvent(Event event) {
        eventMessaging.createEvent(event);
        return event;
    }


    @Override
    public Event updateEvent(Long eventId, Event event) {
        event.setId(eventId);
        eventMessaging.updateEvent(event);
        return event;
    }

    @Override
    public Event getEvent(Long eventId) {
        return eventRepo.findById(eventId).orElseThrow(() -> new
                EventNotFoundException("Event with id:" + eventId + " not found"));
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventMessaging.deleteEvent(eventId);
    }

    @Override
    public Iterable<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepo.findAllByTitle(title);
    }
}
