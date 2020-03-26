package simplon.co.linkinreal.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import simplon.co.linkinreal.dto.ParticipantDto;
import simplon.co.linkinreal.model.Participant;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {


    @Mapping(source = "event.id", target = "eventId")
    ParticipantDto toDto(Participant participant);

    @Mapping(source = "eventId", target = "event.id")
    Participant toEntity(ParticipantDto participantDto);

}



