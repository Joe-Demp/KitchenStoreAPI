package ie.dempsey.kitchenstore.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    private String name = "";
    private String description = "";
    private Type type = Type.CUPBOARD;

    // todo consider changing this to a product count object
    @OneToMany(mappedBy = "house")
    @JsonBackReference
    private List<Product> products = new ArrayList<>();

    @ManyToMany(mappedBy = "houses")
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public House setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public House setCreated(Date created) {
        this.created = created;
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
