package simplon.co.linkinreal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import simplon.co.linkinreal.dto.ParticipantDto;
import simplon.co.linkinreal.model.Participant;
import simplon.co.linkinreal.service.ParticipantService;

import java.util.List;

@RestController//Pour que Spring reconnaisse le Controller ?
@RequestMapping("api/linkinreal/participant")
@CrossOrigin("*")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;


    @GetMapping
    public List<Participant> getParticipant() {
        return participantService.getParticipant();
    }


    @GetMapping("/{eventId}")
    public List<Participant> getParticipantsByEventId(@PathVariable Long eventId) {

        return participantService.findParticipantsByEventId(eventId);
    }

    @PostMapping
    public ResponseEntity<?> createParticipant(@RequestBody ParticipantDto participant) {
        try {
            return ResponseEntity.ok(participantService.createParticipant(participant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{participantId}")
    public ResponseEntity<Participant> deleteEvent(@PathVariable Long participantId) {
        if (participantService.deleteParticipant(participantId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
