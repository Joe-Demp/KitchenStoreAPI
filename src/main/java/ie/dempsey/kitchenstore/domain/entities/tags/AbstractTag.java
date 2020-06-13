package ie.dempsey.kitchenstore.domain.entities.tags;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tag")
public abstract class AbstractTag implements Tag {
    public String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int priority;

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
}
