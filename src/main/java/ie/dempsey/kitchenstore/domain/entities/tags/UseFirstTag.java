package ie.dempsey.kitchenstore.domain.entities.tags;

public class UseFirstTag implements Tag {
    private static UseFirstTag instance;

    private UseFirstTag() {
    }

    public static UseFirstTag getInstance() {
        if (instance == null) {
            instance = new UseFirstTag();
        }
        return instance;
    }

    // TODO implement these methods properly
    @Override
    public String name() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UseFirstTag) {
            return o == instance;
        }
        return false;
    }
}
