package simplon.co.linkinreal.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.exception.EntityNotFoundException;
import simplon.co.linkinreal.model.EventCategory;
import simplon.co.linkinreal.service.EventCategoryService;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/linkinreal/eventcategory")
@RestController
public class EventCategoryController {

    EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {

        this.eventCategoryService = eventCategoryService;
    }


    @GetMapping
    public Page<EventCategory> getEventCategory(@ApiParam(value = "Query param for 'pageNumber'") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                @ApiParam(value = "Query param for 'pageSize'") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                @ApiParam(value = "Query param for 'sort' criteria") @Valid @RequestParam(value = "sort", required = false) String criteria,
                                                @ApiParam(value = "Query param for 'sort' direction") @Valid @RequestParam(value = "direction", required = false) String direction) {
        return eventCategoryService.getEventCategory(pageNumber, pageSize, criteria, direction);
    }


    @GetMapping("{eventCategoryId}")
    public EventCategory getEventCategory(Long eventCategoryId) {
        return eventCategoryService.getEventCategoryById(eventCategoryId);
    }

    @PostMapping
    public ResponseEntity<EventCategory> createEventCategory(EventCategory eventCategory) {
        try {
            return ResponseEntity.ok(eventCategoryService.createEventCategory(eventCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("{eventCategoryId}")
    public ResponseEntity<EventCategory> updateEventCategory(EventCategory eventCategory, Long eventCategoryId) {

        try {
            return ResponseEntity.ok(eventCategoryService.updateEventCategory(eventCategory, eventCategoryId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("{eventCategoryId}")
    public ResponseEntity<EventCategory> deleteEventCategory(Long eventCategoryId) {
        if (eventCategoryService.deleteEventCategory(eventCategoryId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

















