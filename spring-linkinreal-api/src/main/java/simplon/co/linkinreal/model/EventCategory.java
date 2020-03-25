package simplon.co.linkinreal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(indexes = @Index(columnList = "category", unique = true))
public class EventCategory {

    @Id
    @SequenceGenerator(name = "event_category_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_category_seq_id")
    private Long id;


    @Column(nullable = false)
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "eventCategory")
    private List<Event> events = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
