package simplon.co.linkinreal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Creator {

    @Id
    @SequenceGenerator(name = "creator_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creator_seq_id")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;


    @OneToMany(mappedBy = "creator")
    private List<Event> events = new ArrayList<>();

}
