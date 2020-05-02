package ie.dempsey.kitchenstore.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date joined;
    private String password;
    @ManyToMany
//    @JoinTable(
//            name = "user_owns",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "house_id")
//    )
    private Set<House> houses = new HashSet<>();
}
