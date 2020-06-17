package ie.dempsey.kitchenstore.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.AbstractTag;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductDto implements Serializable {
    public long id;
    public Date created;
    public Date expiry;
    public String name;
    public String description;
    public int quantity;
    public long houseId;
    public Set<AbstractTag> tags;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        id = product.getId();
        created = product.getCreated();
        expiry = product.getExpiry();
        name = product.getName();
        description = product.getDescription();
        quantity = product.getQuantity();
        houseId = product.getHouse().getId();
        tags = castAllAsAbstract(product.getTags());
    }

    public Product project() {
        return new Product()
                .setCreated(created)
                .setExpiry(expiry)
                .setName(name)
                .setDescription(description)
                .setQuantity(quantity)
                ;
        // todo decide what to do about tags here.
    }

    public Product project(House house) {
        Product projected = project();
        return projected.setHouse(house);
    }

    private Set<AbstractTag> castAllAsAbstract(Set<Tag> Itags) {
        Set<AbstractTag> newTagSet = new HashSet<>();
        for (Tag tag : Itags) {
            if (tag instanceof AbstractTag) {
                AbstractTag aTag = (AbstractTag) tag;
                newTagSet.add(aTag);
            }
        }
        return newTagSet;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return id == 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public Set<AbstractTag> getTags() {
        return tags;
    }

    public void setTags(Set<AbstractTag> tags) {
        this.tags = tags;
    }
}
