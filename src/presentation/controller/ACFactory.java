package presentation.controller;

/**
 * Created by salvatore on 08/10/15.
 */
public class ACFactory {

    private ACFactory() {

    }

    public static ApplicationController getAC() {
        return new ACCarLoan();
    }
}
