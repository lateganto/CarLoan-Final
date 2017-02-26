package presentation.controller;

/**
 * Created by salvatore on 10/10/15.
 */
public class FCFactory {

    private FCFactory() {

    }

    public static FrontController getFC() {
        return FCCarLoan.getInstance();
    }
}
