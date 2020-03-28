package simplon.co.linkinreal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @SequenceGenerator(name = "event_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    private String description;

    @Column
    private int participantNb;


    @JsonIgnore
    @OneToMany (mappedBy = "event")
    private List<Participant> participants = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Creator creator;

    @ManyToOne(cascade = CascadeType.ALL)
    private EventCategory eventCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    private Place place;



    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getParticipantNb() {
        return participantNb;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Creator getCreator() {
        return creator;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public Place getPlace() {
        return place;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParticipantNb(int participantNb) {
        this.participantNb = participantNb;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
