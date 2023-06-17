package test;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;

import java.util.Arrays;

public enum Main {
    INSTANCE;

    final EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        int epochs = 100;
        double[] times = new double[epochs];
        INSTANCE.eventBus.subscribe(INSTANCE, TestEvent.class, event -> {});
        for (int j = 0; j < epochs; j++) {
            int iterations = 1000000;
            final long current = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                INSTANCE.eventBus.post(new TestEvent());
            }
            final double end = (System.nanoTime() - current) / 1000000.0;
            System.out.println(iterations + " iterations took " + end + "ms");
            times[j] = end;
        }

        System.out.printf("|%-10s|%-10s|%n", "Epoch", "Time");

        for (int i = 0; i < epochs; i++) {
            System.out.printf("|%-10d|%-10.2f|%n", i + 1, times[i]);
        }

        System.out.println("Result of " + epochs + " epochs: " + Arrays.toString(times));
        System.out.printf("Max: %.2fms\n", Arrays.stream(times).max().getAsDouble());
        System.out.printf("Average: %.2fms\n", (Arrays.stream(times).average().getAsDouble()));
        System.out.printf("Min: %.2fms\n", Arrays.stream(times).min().getAsDouble());
    }
}