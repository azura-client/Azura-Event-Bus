package test;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.core.EventPriority;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;

public enum Main {
    INSTANCE;

    final EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        INSTANCE.eventBus.register(INSTANCE);
        final long current = System.nanoTime();
        int iterations = 0;
        for (int i = 0; i < 1000000; i++) {
            INSTANCE.eventBus.call(new TestEvent());
            iterations = i + 1;
        }
        System.out.println(iterations + " iterations took " + ((System.nanoTime() - current) / 1000000.0) + "ms");
    }

    @EventHandler
    public final Listener<TestEvent> testEventListener = e -> {

    };
}