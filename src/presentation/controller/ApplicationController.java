package presentation.controller;

import utility.Packet;
import utility.exception.CommonException;

/**
 * Created by salvatore on 07/10/15.
 */
public interface ApplicationController {

    Object handleRequest(String service, Packet packet) throws CommonException;

}
