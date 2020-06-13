package ie.dempsey.kitchenstore.domain.entities.tags;

public class UseFirstTag extends AbstractTag {
    public UseFirstTag() {
        super(
                "Use First",
                "This product should be used before others of the same kind",
                3
        );
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UseFirstTag;
    }
}
