package simplon.co.linkinreal.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @SequenceGenerator(name = "event_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    private String description;

    @Column(nullable = false)
    private int participantNb ;

    @ManyToOne
    private Creator creator;

    @ManyToOne
    private EventCategory eventCategory;

    @ManyToOne
    private Place place;

}
