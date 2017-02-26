package business.applicationservice;

import business.entity.Utente;
import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.login.Login;
import utility.exception.CommonException;

import java.util.List;


public class ASUtente implements ApplicationService, Login {

    DataAccessObject dao = DAOFactory.getDAO("DAOUtente");

    @Override
    public TransferObject login(Packet loginData) throws CommonException {
        return dao.read(loginData);
    }










    @Override
    public void create(Packet packet) {
        Utente utente = getEntity(packet);
        //faccio le verifiche sull'entity, se va tutto bene,
        TransferObject to = getTO(utente);

        try {
            dao.create(to);
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransferObject read(Packet packetKey) {
        return null;
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



    private Utente getEntity(Packet packet) {
        Utente utente = new Utente();
        //arriva il pacchetto con l'ordine dei dati da mettere in utente con i suoi getter e setter

        return utente;
    }

    private TransferObject getTO(Utente utente) {
        return utente.getData();
    }

}
