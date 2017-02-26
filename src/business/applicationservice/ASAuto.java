package business.applicationservice;

import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.exception.CommonException;

import java.util.List;

/**
 * Created by salvatore on 27/10/15.
 */
public class ASAuto implements ApplicationService {

    private final DataAccessObject dao = DAOFactory.getDAO("DAOAuto");

    @Override
    public void create(Packet packet) throws CommonException {

    }

    @Override
    public TransferObject read(Packet packetKey) throws CommonException {
        return null;
    }

    @Override
    public void update(Packet packet) throws CommonException {

    }

    @Override
    public void delete(Packet packetKey) throws CommonException {

    }

    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {
        return dao.readAll(packet);
    }

    @Override
    public List<TransferObject> find(Packet packet) throws CommonException {
        return null;
    }
}
