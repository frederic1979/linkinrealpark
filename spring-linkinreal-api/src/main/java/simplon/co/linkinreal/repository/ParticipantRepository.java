package simplon.co.linkinreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import simplon.co.linkinreal.model.Participant;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    List<Participant> findParticipantsByEventId(Long eventId);



}
