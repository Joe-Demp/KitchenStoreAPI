package ie.dempsey.kitchenstore.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ie.dempsey.kitchenstore.domain.entities.tags.AbstractTag;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    private Date expiry;
    // todo move name, description to an Embeddable class
    private String name = "";
    private String description = "";
    private int quantity = 0;

    @ManyToOne
    @JsonManagedReference
    private House house;

    @OneToMany
    private Set<AbstractTag> tags = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
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

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public Product setHouse(House house) {
        this.house = house;
        return this;
    }

    public Set<Tag> getTags() {
        Set<? extends Tag> tagsToReturn = tags;
        return (Set<Tag>) tagsToReturn;
    }

    public Product setTags(Set<Tag> tags) {
        Set<AbstractTag> newTagSet = new HashSet<>();
        for (Tag tag : tags) {
            if (tag instanceof AbstractTag) {
                AbstractTag aTag = (AbstractTag) tag;
                newTagSet.add(aTag);
            }
        }

        this.tags = newTagSet;
        return this;
    }
}
