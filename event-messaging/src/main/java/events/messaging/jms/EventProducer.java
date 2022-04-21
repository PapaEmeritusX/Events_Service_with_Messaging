package events.messaging.jms;

import events.EventMessaging;
import events.data.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.broker.OrderedMessageChannelDecorator;
import org.springframework.stereotype.Component;

@Component
@Profile("artemis-template")
public class EventProducer implements EventMessaging {


    private JmsTemplate jms;

    @Autowired
    public EventProducer(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void createEvent(Event event) {
        jms.convertAndSend("events.create.queue", event);
    }

    @Override
    public void updateEvent(Event event) {
        jms.convertAndSend("events.update.queue", event);
    }

    @Override
    public void deleteEvent(Long id) {
        jms.convertAndSend("events.delete.queue", id);
    }
}
