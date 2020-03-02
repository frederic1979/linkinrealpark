package simplon.co.linkinreal.service;

import org.springframework.stereotype.Service;
import simplon.co.linkinreal.exception.PlaceNotFoundException;
import simplon.co.linkinreal.model.Place;
import simplon.co.linkinreal.repository.PlaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    PlaceRepository placeRepository;

    public PlaceServiceImpl (PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> getPlaceList(){
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> getPlaceById(Long idPlaceToFind){
        return placeRepository.findById(idPlaceToFind);
    }

    @Override
    public Place createPlace(Place placeToCreate) {
        return placeRepository.save(placeToCreate);
    }

    @Override
    public boolean deletePlace(Long idPlaceToDelete) {

        Optional<Place> placeToDelete = placeRepository.findById(idPlaceToDelete);
        if (placeToDelete.isPresent()){
            placeRepository.deleteById(idPlaceToDelete);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Place updatePlace (Long idPlaceToUpdate, Place placeToUpdate) throws PlaceNotFoundException {
        boolean isPlaceExist = placeRepository.existsById(idPlaceToUpdate);

        if (isPlaceExist && placeToUpdate.getId().equals(idPlaceToUpdate)) {
            return placeRepository.save(placeToUpdate);
        } else {
            throw new PlaceNotFoundException();
        }
    }


}
