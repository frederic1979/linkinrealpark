package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.EventCategory;


@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Long> {

}
