package utility.login;

import business.to.TransferObject;
import utility.Packet;
import utility.exception.CommonException;

/**
 * Created by salvatore on 06/10/15.
 */
public interface Login {

    /**
     * JavaDoc
     * @param username
     * @param password
     * @return
     */
    //String login(String username, String password);


    /**
     * JavaDoc
     * @param loginData
     * @return
     */
    TransferObject login(Packet loginData) throws CommonException;

}
