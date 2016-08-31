package RemoteApiConsumer;

/**
 * Created by k.benhmida on 30/08/2016.
 */
public class UrlBuildingException extends Exception  {

    public UrlBuildingException(String message) {
        super(message);
    }

    public UrlBuildingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}