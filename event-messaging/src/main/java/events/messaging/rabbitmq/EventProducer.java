package events.messaging.rabbitmq;

import events.EventMessaging;
import events.EventService;
import events.data.Event;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Profile("rabbitmq-template")
public class EventProducer implements EventMessaging {

    private RabbitTemplate rabbit;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

//    private Queue destination;

    @Autowired
    public EventProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
//        this.destination = destination;
    }

    @Override
    public void createEvent(Event event) {
        rabbit.convertAndSend(exchange,"events.create", event, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_EVENTS_CREATE", "WEB");
                return message;
            }
        });
    }

    @Override
    public void updateEvent(Event event) {
        rabbit.convertAndSend(exchange,"events.update", event, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_EVENTS_UPDATE" , "WEBS");
                return message;
            }
        });

    }

    @Override
    public void deleteEvent(Long id) {
        rabbit.convertAndSend(exchange,"events.delete", id, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_EVENTS_DELETE", "WEB");
                return message;
            }
        });

    }
}
