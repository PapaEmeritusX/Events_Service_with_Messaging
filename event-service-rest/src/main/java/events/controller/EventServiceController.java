package events.controller;

import events.EventMessaging;
import events.EventService;
import events.EventServiceImpl;
import events.data.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/events")
//@CrossOrigin(origins = "http://eventshost:9090")
@Tag(name = "Events Controller", description = "This REST controller provides services" +
        " to manage events in the Events Service application")
public class EventServiceController {

    private EventService eventService;

    @Autowired
    public EventServiceController(@Qualifier("DEFAULT") EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    @Operation(summary = "Creates a new event in the Events Service application")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
//        Event eventCreated = eventService.createEvent(event);
        eventService.createEvent(event);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(event);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the event details in the Events Service application")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") @NotBlank Long id, @Valid @RequestBody Event event) {
        eventService.updateEvent(id, event);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(event);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Provides event details for the supplied event id from " +
            "Events Service application")
    public ResponseEntity<?> eventById(@PathVariable("id") Long id) {
        Event event = eventService.getEvent(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(event);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the event details for the supplied event id from Events Service application")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Provides all events available in Event Service application")
    public Iterable<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(params="title")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Provides the event details for the supplied title from " +
            "Event Service application")
    public Iterable<Event> getAllEventsByTitle(@RequestParam("title") String title) {
        return eventService.getAllEventsByTitle(title);
    }




}
