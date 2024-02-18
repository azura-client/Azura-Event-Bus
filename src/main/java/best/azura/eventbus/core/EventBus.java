package best.azura.eventbus.core;

import best.azura.eventbus.handler.EventExecutable;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventBus {

    /**
     * List of all executables in the event system
     */
    private final CopyOnWriteArrayList<EventExecutable> executables = new CopyOnWriteArrayList<>();

    /**
     * Register an object in the event system
     *
     * @param object the object to register
     */
    public void subscribe(final Object object) {
        if (subscribed(object)) return;
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)
                    || method.getParameterCount() <= 0) continue;
            executables.add(new EventExecutable(method, object, method.getDeclaredAnnotation(EventHandler.class).value()));
        }
        for (final Field field : object.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(EventHandler.class)
                    || !field.getType().isAssignableFrom(Listener.class)) continue;

            executables.add(new EventExecutable(field, object, field.getDeclaredAnnotation(EventHandler.class).value()));
        }
        executables.sort(Comparator.comparingInt(EventExecutable::getPriority));
    }

    /**
     * Register an object in the event system
     *
     * @param object     the object to register
     * @param eventClass the event class to listen to
     * @param listener   the listener to call
     * @param priority   the priority of the listener
     */
    public <U extends Event> void subscribe(final Object object, final Class<U> eventClass, final Listener<U> listener, final EventPriority priority) {
        executables.add(new EventExecutable(eventClass, listener, object, priority));
        executables.sort(Comparator.comparingInt(EventExecutable::getPriority));
    }

    /**
     * Register an object in the event system
     *
     * @param object     the object to register
     * @param eventClass the event class to listen to
     * @param listener   the listener to call
     *                   Uses the default priority
     */
    public <U extends Event> void subscribe(final Object object, final Class<U> eventClass, final Listener<U> listener) {
        this.subscribe(object, eventClass, listener, EventPriority.DEFAULT);
    }

    /**
     * Method used for calling events
     *
     * @param event the event that should be called
     */
    public <U extends Event> U post(final U event) {
        for (EventExecutable eventExecutable : executables) {
            if (eventExecutable.getListener() != null)
                eventExecutable.getListener().call(event);
            if (eventExecutable.getMethod() != null)
                eventExecutable.getMethod().call(event);
        }
        return event;
    }

    /**
     * Unregister an object from the event system
     *
     * @param object the object to unregister
     */
    public void unsubscribe(final Object object) {
        if (!subscribed(object)) return;
        executables.removeIf(e -> e.getParent().equals(object));
    }

    /**
     * Method used to check whether an object is registered in the event system
     *
     * @param object the object to check
     */
    public boolean subscribed(final Object object) {
        return executables.stream().anyMatch(e -> e.getParent().equals(object));
    }

}