package simplon.co.linkinreal.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.exception.EntityNotFoundException;
import simplon.co.linkinreal.exception.EventExceptionNotFound;
import simplon.co.linkinreal.model.Creator;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.model.EventCategory;
import simplon.co.linkinreal.repository.CreatorRepository;
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
    private CreatorRepository creatorRepository;

    //Constructeur, injection de dépendances repository dans eventRepository (attribut)
    public EventServiceImpl(EventRepository eventRepository,
                            EventCategoryRepository eventCategoryRepository,
                            CreatorRepository creatorRepository) {
        this.eventRepository = eventRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.creatorRepository = creatorRepository;
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

    /**
     * Persisting an Event.
     * If the category attribute from EventCategory model or the nicName/email from Creator model
     * already exists in DB, they are not created, but only linked to the Event persisted
     * @param event the Event to Persist
     * @return the Event persisted
     */
    @Override
    public Event createEvent(Event event) {
        /*getting the specific EventCategory (and Id) associated with the category retrieved in the POST*/
        Optional<EventCategory> optionalEventCategory =
                eventCategoryRepository.findByCategory(event.getEventCategory().getCategory());

        /*getting the specific Creator (and Id) associated with the nickName and email retrieved in the POST*/
        Optional<Creator> optionalCreator =
                creatorRepository.findByNickNameAndEmail(event.getCreator().getNickName(), event.getCreator().getEmail());

        /*if the category or the nicName/email is already present in DB */
        if (optionalEventCategory.isPresent() | optionalCreator.isPresent()){

           /*Creating an new Event Object with attributes retrieved from the POST request*/
            Event eventToPersist = new Event();
            eventToPersist.setDate(event.getDate());
            eventToPersist.setDescription(event.getDescription());
            eventToPersist.setParticipantNb(event.getParticipantNb());
            eventToPersist.setPlace(event.getPlace());

            /* If the category String value of the event we want to create already exists in DB,
            we search for the specific object EventCategory in DB with its specific Id
             and affect it to the EventCategory of the Event we want to create*/
            if (optionalEventCategory.isPresent()){

                EventCategory eventCategoryToUpdate = optionalEventCategory.get();
                eventToPersist.setEventCategory(eventCategoryToUpdate);
            } else {
                eventToPersist.setEventCategory(event.getEventCategory());
            }
            /* idem if the creator is already existing in Database */
            if (optionalCreator.isPresent()){

                Creator creatorToUpdate = optionalCreator.get();
                eventToPersist.setCreator(creatorToUpdate);
            } else {
                eventToPersist.setCreator(event.getCreator());
            }
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
