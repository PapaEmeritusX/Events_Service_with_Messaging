package events;

import events.data.Event;
import events.data.Speaker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import events.repository.EventRepository;
import events.repository.SpeakerRepository;

import java.time.LocalDateTime;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(EventRepository eventRepo, SpeakerRepository speakerRepo) {
        return  args -> {

            Speaker tom = new Speaker();
            tom.setFirstName("Thomas");
            tom.setLastName("Root");

            Speaker jan = new Speaker();
            jan.setFirstName("Jan");
            jan.setLastName("Johansson");

            speakerRepo.save(tom);
            speakerRepo.save(jan);

            Event event1 = new Event();
            event1.setTitle("Epam Spring");
            event1.setEventType(Event.EventType.OPEN);
            event1.setPlace("Oybek");
            event1.setDateTime(LocalDateTime.of(2022, 5, 5, 17, 30));
            event1.setSpeaker(jan);

            Event event2 = new Event();
            event2.setTitle("Megalul");
            event2.setEventType(Event.EventType.CLOSED);
            event2.setPlace("Tashkent");
            event2.setDateTime(LocalDateTime.of(2022, 6, 3, 9, 0 ));
            event2.setSpeaker(tom);

            eventRepo.save(event1);
            eventRepo.save(event2);






        };
    }
}
