package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
