import best.azura.eventbus.core.EventBus;

public class Test {

    static final EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        final TestObject object = new TestObject();
        eventBus.register(object);
        eventBus.call(new TestEvent());
        eventBus.unregister(object);
    }

}