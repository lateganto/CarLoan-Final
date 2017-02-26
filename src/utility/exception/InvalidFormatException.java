package utility.exception;

/**
 * Created by salvatore on 22/10/15.
 */
public class InvalidFormatException extends Exception {

    public InvalidFormatException() {
        super("Formato errato");
    }

    public InvalidFormatException(String msg) {
        super(msg);
    }

}
