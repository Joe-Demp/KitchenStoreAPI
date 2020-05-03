package ie.dempsey.kitchenstore.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreationTimestamp
    private Date created;
    private Date expiry;
    // todo move name, description to an Embeddable class
    private String name = "";
    private String description = "";
    @ManyToOne
    @JsonManagedReference
    private House house;

    public long getId() {
        return id;
    }

    public Product setId(long id) {
        this.id = id;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Product setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getExpiry() {
        return expiry;
    }

    public Product setExpiry(Date expiry) {
        this.expiry = expiry;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public Product setHouse(House house) {
        this.house = house;
        return this;
    }
}
