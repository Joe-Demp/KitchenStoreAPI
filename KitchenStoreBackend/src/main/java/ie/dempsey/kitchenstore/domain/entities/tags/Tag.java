package ie.dempsey.kitchenstore.domain.entities.tags;

public interface Tag {
    String name();

    String description();

    /**
     * A number representing how important the tag is.
     *
     * <p>The interpretation of priority lies with the implementing classes</p>
     */
    int priority();
}
