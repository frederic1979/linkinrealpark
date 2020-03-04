package simplon.co.linkinreal.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.model.Creator;

import java.util.Optional;

@Service
public interface CreatorService {

    Page<Creator> getCreators(Integer pageNumber, Integer pageSize, String criteria, String direction);

    Optional<Creator> getCreatorById (Long idCreatorToFind);

    Creator createCreator (Creator creatorToCreate);

    boolean deleteCreator (Long idCreatorToDelete);

    Creator updateCreator (Long idCreatorToUpdate, Creator creatorToUpdate);
}
