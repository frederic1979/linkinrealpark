package simplon.co.linkinreal.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface EventService {


    /**
     * Get the complete list of events.
     *
     * @return the complete list from persistence layer.
     */
    List<Event> getEvents();

    Optional<Event> getEventById(Long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event, Long eventId);

    Boolean deleteEvent(Long eventId);

}
