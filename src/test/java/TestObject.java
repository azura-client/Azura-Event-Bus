import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;

public class TestObject {
    @EventHandler
    public void onTestEvent(TestEvent testEvent) {
        System.out.println("Test event fired from method!");
    }

    @EventHandler
    public Listener<TestEvent> testEventListener = event -> System.out.println("Test event fired from listener!");
}