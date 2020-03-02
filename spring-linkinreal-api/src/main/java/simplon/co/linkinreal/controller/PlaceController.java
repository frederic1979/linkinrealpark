package simplon.co.linkinreal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.exception.PlaceNotFoundException;
import simplon.co.linkinreal.model.Place;
import simplon.co.linkinreal.service.PlaceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    PlaceService placeService;

    public PlaceController(PlaceService placeService){
        this.placeService = placeService;
    }

    @GetMapping
    public List<Place> getListPlace(){
        return placeService.getPlaceList();
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long placeId){

        Optional<Place> placeObs = placeService.getPlaceById(placeId);

        if (placeObs.isPresent()){
            return ResponseEntity.ok(placeObs.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Place> addPlace (@RequestBody Place placeToCreate) {
        Place createdPlace = placeService.createPlace(placeToCreate);
        if (createdPlace != null) {
            return ResponseEntity.ok(createdPlace);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Place> deletePlace (@PathVariable Long placeId){
        boolean isDeleted = placeService.deletePlace(placeId);

        if (isDeleted){
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{placeId}")
    public ResponseEntity<Place> updatePlace (@PathVariable Long placeId, @RequestBody Place placeToUpdate) {

        try {
            Place placeUpdated = placeService.updatePlace(placeId, placeToUpdate);
            return ResponseEntity.ok(placeUpdated);
        } catch (PlaceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
