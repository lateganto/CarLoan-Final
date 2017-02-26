package integration.dao;

import business.to.TransferObject;
import utility.Packet;
import utility.exception.CommonException;

import java.util.List;


public interface DataAccessObject {

    void create(TransferObject data) throws CommonException;

    TransferObject read(Packet packet) throws CommonException;

    void update(TransferObject data) throws CommonException;

    void delete(String id) throws CommonException;

    List<TransferObject> readAll(Packet packet) throws CommonException;

    List<TransferObject> find(String mainInfo) throws CommonException;

}
