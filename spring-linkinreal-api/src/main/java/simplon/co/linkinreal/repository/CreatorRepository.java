package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
