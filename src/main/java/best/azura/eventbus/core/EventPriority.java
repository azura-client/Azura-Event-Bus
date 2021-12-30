package best.azura.eventbus.core;

public enum EventPriority {
    //Priority of the events
    HIGHEST(0),
    HIGHER(1),
    DEFAULT(2),
    LOWER(3),
    LOWEST(4);

    private final int priority;

    EventPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}