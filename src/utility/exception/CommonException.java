package utility.exception;

import utility.dialog.Dialog;

/**
 * Created by salvatore on 23/10/15.
 */
public class CommonException extends Exception {

    public CommonException() {
        super("Errore Imprevisto, riavviare CarLoan!");
    }

    public CommonException(String msg) {
        super(msg);
    }

    public void reportException() {
        Dialog.showErrorDialog(getMessage());
    }
}
