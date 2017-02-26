package presentation.controller;

import utility.Packet;
import utility.exception.CommonException;

/**
 * Created by salvatore on 10/10/15.
 */
public class FCCarLoan implements FrontController {

    private static FCCarLoan frontController;

    private FCCarLoan() {

    }

    public static FCCarLoan getInstance() {
        if (frontController == null) {
            frontController = new FCCarLoan();
        }

        return frontController;
    }

    @Override
    public Object processRequest(String request, Packet packet) throws CommonException {
        ApplicationController ac = ACFactory.getAC();
        return ac.handleRequest(request, packet);
    }

    /*
    public static void main(String args[]) {


        FCCarLoan fcCarLoan = (FCCarLoan) FCFactory.getFC();

        Packet packet = new Packet();
        packet.set("username", "salvatore");
        packet.set("password", Cryptographer.encrypt("password"));

        System.out.print(fcCarLoan.processRequest("login", packet));
    }
    */

}
