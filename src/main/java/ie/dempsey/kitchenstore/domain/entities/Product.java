package ie.dempsey.kitchenstore.domain.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Product() {
    }

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
}
