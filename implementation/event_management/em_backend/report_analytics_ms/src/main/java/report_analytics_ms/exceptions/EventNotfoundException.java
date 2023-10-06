package report_analytics_ms.exceptions;

public class EventNotfoundException extends RuntimeException {
    public static EventNotfoundException getEventNotFoundException(){
        return new EventNotfoundException();
    }
    public EventNotfoundException() {
        super();
    }
}
