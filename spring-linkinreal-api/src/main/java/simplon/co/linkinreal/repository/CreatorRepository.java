package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.Creator;

import java.util.Optional;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {

    /*searching if a Creator with a specific combination of nickName/email attributes already exists */
    Optional<Creator> findByNickNameAndEmail(String nickNameToSearch, String emailToSearch);
}
