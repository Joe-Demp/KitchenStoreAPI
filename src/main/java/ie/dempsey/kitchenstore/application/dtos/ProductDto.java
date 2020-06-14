package ie.dempsey.kitchenstore.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.AbstractTag;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    public static final ProductDto EMPTY = new ProductDto();
    public long id;
    public Date created;
    public Date expiry;
    public String name;
    public String description;
    public int quantity;
    public House house;
    public Set<AbstractTag> tags;

    private ProductDto() {
    }

    // todo, sort out how this will handle House
    public ProductDto(Product product) {
        id = product.getId();
        created = product.getCreated();
        expiry = product.getExpiry();
        name = product.getName();
        description = product.getDescription();
        quantity = product.getQuantity();
        tags = castAllAsAbstract(product.getTags());
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
}
