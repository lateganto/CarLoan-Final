package presentation.controller;

import utility.Packet;
import utility.exception.CommonException;

/**
 * Created by salvatore on 10/10/15.
 */
public interface FrontController {

    //TODO potrebbe sollevare qualche eccezione
    Object processRequest(String request, Packet packet) throws CommonException;
}
