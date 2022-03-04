package test;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;

public enum Main {
    INSTANCE;

    final EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        INSTANCE.eventBus.register(INSTANCE);
        INSTANCE.eventBus.call(new TestEvent());
        INSTANCE.eventBus.call(new TestEvent1());
    }

    @EventHandler
    public final Listener<TestEvent> testEventListener = e -> {
        System.out.println("Listener call on test event " + e.getClass().getSimpleName());
    };

    @EventHandler
    public final Listener<TestEvent1> testEvent1Listener = e -> {
        System.out.println("Listener call on test event 1 " + e.getClass().getSimpleName());
    };

}