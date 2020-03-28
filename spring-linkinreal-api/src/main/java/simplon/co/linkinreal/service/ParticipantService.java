package simplon.co.linkinreal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.dto.ParticipantDto;
import simplon.co.linkinreal.model.Participant;

import java.util.List;

@Service
public interface ParticipantService {


    ResponseEntity<?> createParticipant(ParticipantDto participant);

    List<Participant> getParticipant();

    List<Participant> findParticipantsByEventId(Long eventId);
}
