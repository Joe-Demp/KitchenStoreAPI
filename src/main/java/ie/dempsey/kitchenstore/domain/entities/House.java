package ie.dempsey.kitchenstore.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Type type = Type.CUPBOARD;
    @OneToMany(mappedBy = "house")
    private List<Product> products = new ArrayList<>();
    @ManyToMany(mappedBy = "houses")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public House setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public House setDescription(String description) {
        this.description = description;
        return this;
    }

    public Type getType() {
        return type;
    }

    public House setType(Type type) {
        this.type = type;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public House setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public House setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    public enum Type {CUPBOARD, FRIDGE, FREEZER}
}
