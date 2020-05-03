package ie.dempsey.kitchenstore.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name = "";
    private Date joined;
    private String password;
    private Status status = Status.REGULAR;
    @ManyToMany
    private Set<House> houses = new HashSet<>();

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Date getJoined() {
        return joined;
    }

    public User setJoined(Date joined) {
        this.joined = joined;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public User setHouses(Set<House> houses) {
        this.houses = houses;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public User setStatus(Status status) {
        this.status = status;
        return this;
    }

    public User makeAdmin() {
        status = Status.ADMIN;
        return this;
    }

    public User makeRegular() {
        status = Status.REGULAR;
        return this;
    }

    public boolean isAdmin() {
        return status == Status.ADMIN;
    }

    public enum Status {ADMIN, REGULAR}
}
