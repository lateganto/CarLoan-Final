package presentation.controller;

import business.applicationservice.ASInvoker;
import business.applicationservice.ASInvokerFactory;
import presentation.boundary.Boundary;
import presentation.boundary.BoundaryFactory;
import utility.Packet;
import utility.exception.CommonException;
import utility.reader.XMLReader;
import utility.security.Cryptographer;

/**
 * Created by salvatore on 07/10/15.
 */
public class ACCarLoan implements ApplicationController {

    private static String SHOW_GUI_SYNTAX = "show[a-zA-Z]+";

    @Override
    public Object handleRequest(String request, Packet packet) throws CommonException {
        Object result = null;


        //TODO controllare e rimuovere spazzatura
        //try {

            if (request.matches(SHOW_GUI_SYNTAX)) {
                //TODO da spostare...
                XMLReader xmlReader = XMLReader.getReader("boundary");
                Boundary boundary = BoundaryFactory.getBoundary(xmlReader.getBoundaryValue(request), packet);
                return boundary.showWindow(packet);

            } else {
                ASInvoker asInvoker = ASInvokerFactory.getASInvoker(request);
                result = asInvoker.invoke(request, packet);
            }

            /*if (result == null) {
                result = true;
            }*/

        //}

        return result;
    }

    /*
    private Object execute(String request, Packet packet) /* throws CommonException{
        ASInvoker asInvoker = ASInvokerFactory.getASInvoker(request);
        return asInvoker.invoke(request, packet);
    }

    */
    /*
    private static Object dispatchGUI(String service, Packet packet) {
        Boundary boundary = BoundaryFactory.getBoundary(service, packet);
        return boundary.showWindow(packet);
    }
    */


    public static void main(String args[]) {
        ACCarLoan acCarLoan = (ACCarLoan) ACFactory.getAC();

        Packet packet = new Packet();
        packet.set("username", "salvatore");
        packet.set("password", Cryptographer.encrypt("password"));

        //System.out.print(acCarLoan.handleRequest("login", packet));

        //System.out.print(acCarLoan.handleRequest("showHomeDipendente", packet));


    }

}
