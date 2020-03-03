package simplon.co.linkinreal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.exception.CreatorNotFoundException;
import simplon.co.linkinreal.model.Creator;
import simplon.co.linkinreal.repository.CreatorRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreatorServiceImpl implements CreatorService {

    private CreatorRepository creatorRepository;

    public CreatorServiceImpl(CreatorRepository creatorRepository){
        this.creatorRepository = creatorRepository;
    }

    @Override
    public Page<Creator> getCreators(Integer pageNumber, Integer pageSize, String criteria, String direction) {
        // If page number is not null then use it for paging, otherwise provide page 0
        int pNumber = (pageNumber != null) ? pageNumber : 0;
        // If page size is not null then use it for paging, otherwise use default 3 page size
        int pSize = (pageSize != null) ? pageSize : 3;

        // By default sort on nickName
        String sortingCriteria = "nickName";

        // If sorting criteria matches a creator field name, then use it for sorting
        Field[] fields = Creator.class.getDeclaredFields();
        List<String> possibleCriteria = new ArrayList<>();
        for (Field field : fields) {
            possibleCriteria.add(field.getName().toLowerCase());
        }
        if (criteria != null && possibleCriteria.contains(criteria)) {
            sortingCriteria = criteria;
        }

        // By default sorting ascending, but if user explicitely choose desc, then sort descending
        Sort.Direction sortingDirection = Sort.Direction.ASC;
        if (direction != null) {
            sortingDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        }

        return creatorRepository.findAll(PageRequest.of(pNumber, pSize, Sort.by(sortingDirection, sortingCriteria)));
    }


    @Override
    public Optional<Creator> getCreatorById(Long idCreatorToFind){
        return creatorRepository.findById(idCreatorToFind);
    }

    @Override
    public Creator createCreator(Creator creatorToCreate) {
        return creatorRepository.save(creatorToCreate);
    }

    @Override
    public boolean deleteCreator(Long idCreatorToDelete) {

        Optional<Creator> creatorToDelete = creatorRepository.findById(idCreatorToDelete);
        if (creatorToDelete.isPresent()){
            creatorRepository.deleteById(idCreatorToDelete);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Creator updateCreator (Long idCreatorToUpdate, Creator creatorToUpdate) throws CreatorNotFoundException {
        boolean isCreatorExist = creatorRepository.existsById(idCreatorToUpdate);

        if (isCreatorExist && creatorToUpdate.getId().equals(idCreatorToUpdate)) {
            return creatorRepository.save(creatorToUpdate);
        } else {
            throw new CreatorNotFoundException();
        }
    }

}
