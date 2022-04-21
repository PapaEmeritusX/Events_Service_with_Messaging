package events.messaging.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.data.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Profile("artemis-template")
@Configuration
public class MessagingConfig {

    private static final Logger logger = LoggerFactory.getLogger(MessagingConfig.class);

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String,Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("event", Event.class);
//        typeIdMappings.put("dateTime", LocalDateTime.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }




}