package ie.dempsey.kitchenstore.domain.entities.tags;

public class PerishableTag implements Tag {
    private static PerishableTag instance;

    private PerishableTag() {
    }

    public static PerishableTag getInstance() {
        if (instance == null) {
            instance = new PerishableTag();
        }
        return instance;
    }

    @Override
    public String name() {
        return "Perishable";
    }

    @Override
    public String description() {
        return "This item is liable to 'go off' soon after purchase";
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PerishableTag) {
            return o == instance;
        }
        return false;
    }
}
