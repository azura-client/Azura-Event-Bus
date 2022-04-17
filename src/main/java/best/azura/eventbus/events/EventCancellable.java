package best.azura.eventbus.events;

import best.azura.eventbus.core.Event;

public abstract class EventCancellable implements Event {
    private boolean cancelled;
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    public boolean isCancelled() {
        return cancelled;
    }
}