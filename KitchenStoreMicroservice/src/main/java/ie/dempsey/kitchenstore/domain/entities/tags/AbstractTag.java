package ie.dempsey.kitchenstore.domain.entities.tags;

import ie.dempsey.kitchenstore.domain.entities.Product;

import javax.persistence.*;

// SINGLE_TABLE is the default
@Entity(name = "tag")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractTag implements Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private int priority;

    @ManyToOne
    private Product owner;

    public AbstractTag() {
    }

    public AbstractTag(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public AbstractTag setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractTag setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AbstractTag setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public AbstractTag setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Product getOwner() {
        return owner;
    }

    public AbstractTag setOwner(Product owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractTag) {
            return name.equals(((AbstractTag) o).name());
        }
        return false;
    }
}
