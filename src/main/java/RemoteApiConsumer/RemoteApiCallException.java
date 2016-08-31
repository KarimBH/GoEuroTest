package RemoteApiConsumer;

/**
 * Created by k.benhmida on 30/08/2016.
 */
public class RemoteApiCallException extends Exception  {

    public RemoteApiCallException(String message) {
        super(message);
    }

    public RemoteApiCallException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
