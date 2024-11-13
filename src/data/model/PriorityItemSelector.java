package data.model;

public class PriorityItemSelector {

    private int priority;
    private String label;

    public PriorityItemSelector(int priority, String label) {
        this.priority = priority;
        this.label = label;
    }

    public int getPriority() {
        return priority;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
