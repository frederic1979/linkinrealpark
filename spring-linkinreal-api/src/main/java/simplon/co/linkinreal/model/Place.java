package simplon.co.linkinreal.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {

    @Id
    @SequenceGenerator(name = "place_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_seq_id")
    private Long id;

    private String label;

    private int streetNumber;

    private String street;

    private int postalCode;

    @OneToMany(mappedBy = "place")
    private List<Event> events = new ArrayList<>();

}
