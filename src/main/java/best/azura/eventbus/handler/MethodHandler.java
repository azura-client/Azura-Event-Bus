package best.azura.eventbus.handler;

import best.azura.eventbus.core.Event;

import java.lang.reflect.Method;

public class MethodHandler {
    private final Object parent;
    private Method method;

    public MethodHandler(Method method, Object parent) {
        this.method = method;
        this.parent = parent;
        if (!this.isValid(this.method)) {
            this.method = null;
            return;
        }
        this.method.setAccessible(true);
    }

    private boolean isValid(Method method) {
        if (method.getParameterCount() == 0) return false;
        if (method.getParameterTypes()[0].getInterfaces().length != 0)
            return this.isValid(method.getParameterTypes()[0].getInterfaces()[0]);
        if (this.isValid(this.method.getParameterTypes()[0]) || this.isValid(this.method.getParameterTypes()[0].getSuperclass()))
            return true;
        return this.isValid(this.method.getParameterTypes()[0].getSuperclass().getInterfaces()[0]);
    }

    private boolean isValid(Class<?> clazz) {
        return clazz.equals(Event.class);
    }


    public void call(final Event event) {
        if (this.method == null) return;
        try {
            this.method.invoke(parent, event);
        } catch (Exception ignored) {}
    }

}