package ie.dempsey.kitchenstore.application.dtos;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HouseDto {
    public long id;
    public Date created;
    public String name;
    public String description;
    public House.Type type;
    public List<Long> productIds;
    public Set<Long> userIds;

    public HouseDto() {
    }

    public HouseDto(House house) {
        id = house.getId();
        created = house.getCreated();
        name = house.getName();
        description = house.getDescription();
        type = house.getType();
        productIds = getIdsFromProducts(house.getProducts());
        userIds = getIdsFromUsers(house.getUsers());
    }

    private List<Long> getIdsFromProducts(List<Product> products) {
        return products.stream().map(Product::getId).collect(Collectors.toList());
    }

    private Set<Long> getIdsFromUsers(Set<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toSet());
    }

    public House project() {
        return new House()
                .setId(id)
                .setCreated(created)
                .setName(name)
                .setDescription(description)
                .setType(type)
                ;
    }

    public House project(List<Product> products, Set<User> users) {
        House projected = project();
        return projected
                .setProducts(products)
                .setUsers(users)
                ;
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

    public House.Type getType() {
        return type;
    }

    public void setType(House.Type type) {
        this.type = type;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}
