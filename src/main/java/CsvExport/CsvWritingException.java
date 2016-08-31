package CsvExport;

/**
 * Created by k.benhmida on 30/08/2016.
 */
public class CsvWritingException extends Exception  {

    public CsvWritingException(String message) {
        super(message);
    }

    public CsvWritingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}