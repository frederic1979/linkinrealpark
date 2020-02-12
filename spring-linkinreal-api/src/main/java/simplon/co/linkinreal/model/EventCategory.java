package simplon.co.linkinreal.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory {

    @Id
    @SequenceGenerator(name = "event_category_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_category_seq_id")
    private Long id;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "eventCategory")
    private List<Event> events = new ArrayList<>();
}
