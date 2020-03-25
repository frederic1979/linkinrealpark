package simplon.co.linkinreal.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.exception.EntityNotFoundException;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.model.EventCategory;
import simplon.co.linkinreal.repository.EventCategoryRepository;
import simplon.co.linkinreal.repository.EventRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private EventCategoryRepository eventCategoryRepository;

    //Constructeur, injection de dépendances repository dans eventRepository (attribut)
    public EventServiceImpl(EventRepository eventRepository,
                            EventCategoryRepository eventCategoryRepository) {
        this.eventRepository = eventRepository;
        this.eventCategoryRepository = eventCategoryRepository;
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
        /*getting the specific EventCategory (and Id) associated with its category value*/
        Optional<EventCategory> optionalEventCategory =
                eventCategoryRepository.findByCategory(event.getEventCategory().getCategory());


        if (optionalEventCategory.isPresent()){

            EventCategory eventCategoryToUpdate = optionalEventCategory.get();

            /*Creating an Event Object with the EventCategory Object already in DB*/
            /*The category String value is the same in DB than in the POST, but here, we */
            /*search for the specific object EventCategory in DB with its specific Id */
            Event eventToPersist = new Event();

            eventToPersist.setDate(event.getDate());
            eventToPersist.setDescription(event.getDescription());
            eventToPersist.setParticipantNb(event.getParticipantNb());
            eventToPersist.setCreator(event.getCreator());
            eventToPersist.setPlace(event.getPlace());
            eventToPersist.setEventCategory(eventCategoryToUpdate);

            return eventRepository.save(eventToPersist);

        } else {
            return eventRepository.save(event);
        }
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


//            Event eventToSave =  eventRepository.save(eventToPersist);

/*updating EventCategory Object adding the Event created in the list of Events */
//            EventCategory eventCategoryToUpdate = optionalEventCategory.get();
//            List<Event> events = eventCategoryToUpdate.getEvents();
//            events.add(eventToSave);
//            eventCategoryToUpdate.setEvents(events);
//            eventCategoryRepository.save(eventCategoryToUpdate);
