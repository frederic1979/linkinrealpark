package simplon.co.linkinreal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.dto.ParticipantDto;
import simplon.co.linkinreal.mappers.ParticipantMapper;
import simplon.co.linkinreal.model.Event;
import simplon.co.linkinreal.model.Participant;
import simplon.co.linkinreal.repository.ParticipantRepository;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {


    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantMapper participantMapper;


    @Override
    public ResponseEntity<?> createParticipant(ParticipantDto participantDto) {
        if (participantDto.getNickName() == "" | participantDto.getNickName().length()>10 ) {
            return ResponseEntity.badRequest().body("this empty nickName is not allowed ");
        } else {
            for (Participant participant1 : findParticipantsByEventId(participantDto.getEventId())) {
                if (participant1.getNickName().equals(participantDto.getNickName())) {
                    return ResponseEntity.badRequest().body("this nickName participant already exists for this event");
                }
            }

            Participant participant = participantRepository.save(participantMapper.toEntity(participantDto));

            return ResponseEntity.ok(participantMapper.toDto(participant));
        }
    }

    @Override
    public List<Participant> getParticipant(){
        return participantRepository.findAll();
    }

    @Override
    public List<Participant> findParticipantsByEventId(Long eventId){

        return participantRepository.findParticipantsByEventId(eventId);
    };

    @Override
    public Boolean deleteParticipant(Long participantId) {


        if (participantRepository.existsById(participantId)) {
            participantRepository.deleteById(participantId);
            return true;
        } else {
            return false;
        }
    }

}
