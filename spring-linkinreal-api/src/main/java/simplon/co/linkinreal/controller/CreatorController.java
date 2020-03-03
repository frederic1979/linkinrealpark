package simplon.co.linkinreal.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.exception.CreatorNotFoundException;
import simplon.co.linkinreal.model.Creator;
import simplon.co.linkinreal.service.CreatorService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/creators")
public class CreatorController {

    private CreatorService creatorService;

    public CreatorController (CreatorService creatorService){
        this.creatorService = creatorService;
    }

    @GetMapping
    public Page<Creator> getCreators(
            @ApiParam(value = "Query param for 'pageNumber'") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @ApiParam(value = "Query param for 'pageSize'") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(value = "Query param for 'sort' criteria") @Valid @RequestParam(value = "sort", required = false) String criteria,
            @ApiParam(value = "Query param for 'sort' direction") @Valid @RequestParam(value = "direction", required = false) String direction) {

        return creatorService.getCreators(pageNumber, pageSize, criteria, direction);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<Creator> getCreatorById(@PathVariable Long creatorId){

        Optional<Creator> creatorObs = creatorService.getCreatorById(creatorId);

        if (creatorObs.isPresent()){
            return ResponseEntity.ok(creatorObs.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Creator> addCreator (@RequestBody Creator creatorToCreate) {
        Creator createdCreator = creatorService.createCreator(creatorToCreate);
        if (createdCreator != null) {
            return ResponseEntity.ok(createdCreator);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{creatorId}")
    public ResponseEntity<Creator> deleteCreator (@PathVariable Long creatorId){
        boolean isDeleted = creatorService.deleteCreator(creatorId);

        if (isDeleted){
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{creatorId}")
    public ResponseEntity<Creator> updateCreator (@PathVariable Long creatorId, @RequestBody Creator creatorToUpdate) {

        try {
            Creator creatorUpdated = creatorService.updateCreator(creatorId, creatorToUpdate);
            return ResponseEntity.ok(creatorUpdated);
        } catch (CreatorNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
