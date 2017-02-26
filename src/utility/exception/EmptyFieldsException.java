package utility.exception;

/**
 * Created by salvatore on 22/10/15.
 */
public class EmptyFieldsException extends Exception {

    public EmptyFieldsException() {
        super("Errore campi vuoti");
    }

    public EmptyFieldsException(String msg) {
        super(msg);
    }

}
