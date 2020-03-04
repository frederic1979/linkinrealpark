package simplon.co.linkinreal.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.exception.EntityNotFoundException;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.repository.EventRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    public Page<Event> getEvents(Integer pageNumber, Integer pageSize, String criteria, String direction) {
                //return eventRepository.findAll();
        // If page number is not null then use it for paging, otherwise provide page 0
        int pNumber = (pageNumber != null) ? pageNumber : 0;
        // If page size is not null then use it for paging, otherwise use default 50 page size
        int pSize = (pageSize != null) ? pageSize : 50;

        // By default sort on aliment name
        String sortingCriteria = "date";

        // If sorting criteria matches an aliment field name, then use it for sorting
        Field[] fields = Event.class.getDeclaredFields();
        List<String> possibleCriteria = new ArrayList<>();
        for (Field field : fields) {
            possibleCriteria.add(field.getName().toLowerCase());
        }
        if (criteria != null && possibleCriteria.contains(criteria)) {
            sortingCriteria = criteria;
        }

        // By default sorting ascending, but if user explicitely choose desc, then sort descending
        Sort.Direction sortingDirection = Sort.Direction.ASC;
        if (direction != null) {
            sortingDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        }

        return eventRepository.findAll(PageRequest.of(pNumber, pSize, Sort.by(sortingDirection, sortingCriteria)));
    }


    @Override
    public Event getEventById(Long eventId) throws EntityNotFoundException {
        Optional<Event> event = eventRepository.findById(eventId); //on créé un optional
        if (event.isPresent())
        {return event.get();}
        else throw new EntityNotFoundException("The event with ID: " + eventId + " cannot be found in DB", "Event");
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
