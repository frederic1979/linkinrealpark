package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.EventCategory;

import java.util.Optional;


@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Long> {

    /*searching if a Creator with a specific category attribute already exists */
    Optional<EventCategory> findByCategory(String categoryToSearch);
}
