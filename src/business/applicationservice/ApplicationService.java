package business.applicationservice;

import business.to.TransferObject;
import utility.Packet;
import utility.exception.CommonException;

import java.util.List;

/**
 * Created by salvatore on 06/10/15.
 */
public interface ApplicationService {

    void create(Packet packet) throws CommonException;

    TransferObject read(Packet packetKey) throws CommonException;

    void update(Packet packet) throws CommonException;

    void delete(Packet packetKey) throws CommonException;

    List<TransferObject> readAll(Packet packet) throws CommonException;

    List<TransferObject> find(Packet packet) throws CommonException;

}
