package service.event;

/**
 * Created by manolofernandez on 9/25/17.
 */

public class NewDataEvent {
    private final boolean success;
    private Throwable throwable;

    public NewDataEvent(boolean success) {
        this.success = success;
    }

    public NewDataEvent(boolean success, Throwable throwable) {
        this.success = success;
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
