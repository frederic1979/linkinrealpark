package simplon.co.linkinreal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import simplon.co.linkinreal.exception.EntityNotFoundException;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.model.EventCategory;
import simplon.co.linkinreal.repository.EventCategoryRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    private EventCategoryRepository eventCategoryRepository;

    //Constructeur
    public EventCategoryServiceImpl(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;

    }


    public Page<EventCategory> getEventCategory(Integer pageNumber, Integer pageSize, String criteria, String direction) {
        //return eventRepository.findAll();
        // If page number is not null then use it for paging, otherwise provide page 0
        int pNumber = (pageNumber != null) ? pageNumber : 0;
        // If page size is not null then use it for paging, otherwise use default 50 page size
        int pSize = (pageSize != null) ? pageSize : 50;

        // By default sort on aliment name
        String sortingCriteria = "id";

        // If sorting criteria matches an aliment field name, then use it for sorting
        Field[] fields = EventCategory.class.getDeclaredFields();
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

        return eventCategoryRepository.findAll(PageRequest.of(pNumber, pSize, Sort.by(sortingDirection, sortingCriteria)));
    }

    @Override
    public EventCategory getEventCategoryById(Long eventCategoryId) throws EntityNotFoundException {
        Optional<EventCategory> eventCategory = eventCategoryRepository.findById(eventCategoryId);
        if (eventCategory.isPresent()) {
            return eventCategory.get();
        } else
            throw new EntityNotFoundException("The eventCategory with ID: " + eventCategoryId + " cannot be found in DB", "EventCategory");
    }

    @Override
    public EventCategory createEventCategory(@RequestBody EventCategory eventCategory){
       return eventCategoryRepository.save(eventCategory);
    }

    @Override
    public EventCategory updateEventCategory(EventCategory eventCategory, Long eventCategoryId){
                if (eventCategoryRepository.existsById(eventCategoryId)){
                    eventCategory.setId(eventCategoryId);
                    return eventCategoryRepository.save(eventCategory);
                }
                else throw new EntityNotFoundException("The eventCategory with ID: " + eventCategoryId + " cannot be found in DB", "EventCategory");
    }


    @Override
    public Boolean deleteEventCategory(Long eventCategoryId){
        if (eventCategoryRepository.existsById(eventCategoryId)){
            eventCategoryRepository.deleteById(eventCategoryId);
            return true;
        }else throw new EntityNotFoundException("The eventCategory with ID: " + eventCategoryId + " cannot be found in DB", "EventCategory");

    };


}
