package ie.dempsey.kitchenstore.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(mappedBy = "houses")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public House setId(long id) {
        this.id = id;
        return this;
    }
}
