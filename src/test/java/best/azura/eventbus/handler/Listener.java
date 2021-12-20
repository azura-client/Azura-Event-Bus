package best.azura.eventbus.handler;

/**
 * @author Solastis
 * DATE:19.12.21
 */
public interface Listener<Event> {

    void call(final Event event);

}
