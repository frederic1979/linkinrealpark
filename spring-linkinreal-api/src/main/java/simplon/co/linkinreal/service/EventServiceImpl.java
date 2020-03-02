package simplon.co.linkinreal.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    //Constructeur, injection de dépendances repository dans eventRepository (attribut)
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEvents() {
                return eventRepository.findAll();
    }


    @Override
    public Optional<Event> getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id); //on créé un optional
        return event;
    }


    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(Event event, Long eventId) throws EventExceptionNotFound {
        if (eventRepository.existsById(eventId)) {
            event.setId(eventId);
            return eventRepository.save(event);
        } else
        throw new EventExceptionNotFound();
    }

    @Override
    public Boolean deleteEvent(Long eventId){
        if (eventRepository.existsById(eventId)){
             eventRepository.deleteById(eventId);
             return true;
        }else return false;
    }

}
