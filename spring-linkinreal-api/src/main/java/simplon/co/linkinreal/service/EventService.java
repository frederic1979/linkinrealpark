package simplon.co.linkinreal.service;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;
import simplon.co.linkinreal.model.Event;


@Service
public interface EventService {


    /**
     * Get the complete list of events.
     *
     * @return the complete list from persistence layer.
     */
    Page<Event> getEvents(Integer pageNumber, Integer pageSize, String criteria, String direction);

    Event getEventById(Long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event, Long eventId);

    Boolean deleteEvent(Long eventId);

}
