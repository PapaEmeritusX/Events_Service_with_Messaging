package events;

import events.data.Event;

public interface EventMessaging {

    void createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long id);

}
