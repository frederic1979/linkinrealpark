package simplon.co.linkinreal.controller;


import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.service.EventService;


import javax.validation.Valid;

@RestController//Pour que Spring reconnaisse le Controller ?
@RequestMapping("api/linkinreal/events")
@CrossOrigin("*")
public class EventController {

    private EventService eventService;

    //Constructeur, injection de dépendances service dans eventService (attribut)
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Page<Event> getEvents(
            @ApiParam(value = "Query param for 'pageNumber'") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @ApiParam(value = "Query param for 'pageSize'") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(value = "Query param for 'sort' criteria") @Valid @RequestParam(value = "sort", required = false) String criteria,
            @ApiParam(value = "Query param for 'sort' direction") @Valid @RequestParam(value = "direction", required = false) String direction) {

        return eventService.getEvents(pageNumber, pageSize, criteria, direction);

    }


    @GetMapping("{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);

    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
            return ResponseEntity.ok(eventService.createEvent(event));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{eventId}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long eventId) {
        try {

            return ResponseEntity.ok(eventService.updateEvent(event, eventId));
        } catch (EventExceptionNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long eventId) {
        if (eventService.deleteEvent(eventId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}

