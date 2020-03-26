package simplon.co.linkinreal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simplon.co.linkinreal.dto.ParticipantDto;
import simplon.co.linkinreal.mappers.ParticipantMapper;
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
    public ParticipantDto createParticipant(ParticipantDto participantDto){
        System.out.println("dans service Impl : ");
        Participant participant = participantRepository.save(participantMapper.toEntity(participantDto));

        return participantMapper.toDto(participant);
    }

    @Override
    public List<Participant> getParticipant(){
        return participantRepository.findAll();
    }

    @Override
    public List<Participant> findParticipantsByEventId(Long eventId){
        System.out.println("dans mon service :" + participantRepository.findParticipantsByEventId(eventId));
        return participantRepository.findParticipantsByEventId(eventId);
    };

}
