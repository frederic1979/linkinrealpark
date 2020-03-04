package simplon.co.linkinreal.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.model.EventCategory;

import java.util.List;

@Service
public interface EventCategoryService {
    Page<EventCategory> getEventCategory(Integer pageNumber, Integer pageSize, String criteria, String direction);

    EventCategory getEventCategoryById(Long eventCategoryId);

   EventCategory createEventCategory(EventCategory eventCategory);

 EventCategory updateEventCategory(EventCategory eventCategory, Long eventId);

    Boolean deleteEventCategory(Long eventCategoryId);
}
