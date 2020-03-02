package simplon.co.linkinreal.service;

import org.springframework.stereotype.Service;
import simplon.co.linkinreal.model.Place;
import simplon.co.linkinreal.repository.PlaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaceService {

    List<Place> getPlaceList();

    Optional<Place> getPlaceById (Long idPlaceToFind);

    Place createPlace (Place placeToCreate);

    boolean deletePlace (Long idPlaceToDelete);

    Place updatePlace (Long idPlaceToUpdate, Place placeToUpdate);

}
