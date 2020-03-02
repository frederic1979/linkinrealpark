package simplon.co.linkinreal.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.service.EventService;

import java.util.List;

@RestController//Pour que Spring reconnaisse le Controller ?
@RequestMapping("api/linkinreal/events")
//@CrossOrigin("*")
public class EventController {

    private EventService eventService;

    //Constructeur, injection de dépendances service dans eventService (attribut)
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public List<Event> getEvents() {
        return eventService.getEvents();
    }


    @GetMapping("{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        if (eventService.getEventById(eventId).isPresent()) {
            return ResponseEntity.ok(eventService.getEventById(eventId).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
            return ResponseEntity.ok(eventService.createEvent(event));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{eventId}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long eventId) {
        try {
            /*eventService.update(event, eventId);
            return ResponseEntity.ok().build();*/

            return ResponseEntity.ok(eventService.updateEvent(event, eventId));
        } catch (EventExceptionNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity<Event> deleteEvent(@PathVariable  Long eventId) {
        if (eventService.deleteEvent(eventId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

