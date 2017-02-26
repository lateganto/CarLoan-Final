package business.applicationservice;

import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.exception.CommonException;

import java.util.List;

/**
 * Created by salvatore on 16/10/15.
 */
public class ASDipendente implements ApplicationService {

    DataAccessObject dao = DAOFactory.getDAO("DAODipendente");

    @Override
    public void create(Packet packet) {
    }

    @Override
    public TransferObject read(Packet packetKey) throws CommonException{
        return  dao.read(packetKey);
    }

    @Override
    public void update(Packet packet) {
    }

    @Override
    public void delete(Packet packetKey) {
    }

    @Override
    public List<TransferObject> readAll(Packet packet) {
        return null;
    }

    @Override
    public List<TransferObject> find(Packet packet) {
        return null;
    }
}
