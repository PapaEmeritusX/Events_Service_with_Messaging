package events;

import events.data.Event;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event event);

    Event getEvent(Long eventId);

    void deleteEvent(Long eventId);

    Iterable<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);

}
