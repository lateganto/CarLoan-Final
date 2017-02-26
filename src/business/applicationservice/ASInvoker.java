package business.applicationservice;

import utility.Packet;
import utility.exception.CommonException;

/**
 * Created by salvatore on 10/10/15.
 */
public interface ASInvoker {

    //TODO potrebbe sollevare eccezione
    Object invoke(String request, Packet packet) throws CommonException;
}
