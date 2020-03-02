package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {




}
