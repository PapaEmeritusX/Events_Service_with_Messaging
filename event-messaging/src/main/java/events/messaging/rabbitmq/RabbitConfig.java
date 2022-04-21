package events.messaging.rabbitmq;

import org.jgroups.annotations.MBean;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq-template")
public class RabbitConfig {

    public static final String EVENTS_CREATE_QUEUE = "events.create.queue";
    public static final String EVENTS_UPDATE_QUEUE = "events.update.queue";
    public static final String EVENTS_DELETE_QUEUE = "events.delete.queue";

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue createQueue() {
        return new Queue(EVENTS_CREATE_QUEUE, false);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue(EVENTS_UPDATE_QUEUE, false);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(EVENTS_DELETE_QUEUE, false);
    }

//    @Bean
//    Binding createBinding() {
//        return BindingBuilder.bind(createQueue()).to(defaultExchange()).with("events.create").noargs();
//    }

    @Bean
    public Declarables eventsBindings(DirectExchange defaultExchange,
                                      Queue createQueue,
                                      Queue updateQueue,
                                      Queue deleteQueue) {
        return new Declarables(
                BindingBuilder.bind(createQueue).to(defaultExchange).with("events.create"),
                BindingBuilder.bind(updateQueue).to(defaultExchange).with("events.update"),
                BindingBuilder.bind(deleteQueue).to(defaultExchange).with("events.delete")
        );
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
