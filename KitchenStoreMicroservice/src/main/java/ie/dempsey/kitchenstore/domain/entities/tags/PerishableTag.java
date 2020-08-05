package ie.dempsey.kitchenstore.domain.entities.tags;

public class PerishableTag extends AbstractTag {
    public PerishableTag() {
        super(
                "Perishable",
                "This item is liable to 'go off' soon after purchase",
                2
        );
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PerishableTag;
    }
}
