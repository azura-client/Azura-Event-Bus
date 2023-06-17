package best.azura.eventbus.handler;

import best.azura.eventbus.core.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListenerHandler<T extends Event> {
    private final Type type;
    private final Listener<T> listener;
    private final Class<?> typeClass;

    public ListenerHandler(Type type, Listener<T> listener) {
        this.type = type;
        this.listener = listener;
        if (type instanceof Class<?>)
            this.typeClass = (Class<?>) type;
        else if (type instanceof ParameterizedType)
            this.typeClass = (Class<?>) ((ParameterizedType) this.type).getActualTypeArguments()[0];
        else throw new IllegalArgumentException("Type must be a class or a parameterized type");
    }

    @SuppressWarnings("unchecked")
    public void call(final Event event) {
        if (!this.typeClass.equals(event.getClass()) &&
                !this.typeClass.equals(Event.class)) return;

        this.listener.call((T) event);
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public Type getType() {
        return type;
    }
}