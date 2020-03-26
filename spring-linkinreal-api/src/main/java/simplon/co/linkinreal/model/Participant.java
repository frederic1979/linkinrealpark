package simplon.co.linkinreal.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Participant {

    @Id
    @SequenceGenerator(name = "participant_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_seq_id")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @ManyToOne
    private Event event;

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", event=" + event +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
