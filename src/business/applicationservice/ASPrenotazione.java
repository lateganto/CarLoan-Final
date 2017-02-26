package business.applicationservice;

import business.applicationservice.validator.Validator;
import business.applicationservice.validator.ValidatorFactory;
import business.entity.Prenotazione;
import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.exception.CommonException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by antonio on 27/10/15.
 */
public class ASPrenotazione implements ApplicationService {

    private final DataAccessObject dao = DAOFactory.getDAO("DAOPrenotazione");
    private final Validator validator = ValidatorFactory.getValidator("ValidatorPrenotazione");

    @Override
    public void create(Packet packet) throws CommonException {
        Prenotazione prenotazione = getEntity(packet);
        validator.validate(prenotazione);
        TransferObject to = getTO(prenotazione);

        dao.create(to);
    }

    @Override
    public TransferObject read(Packet packetKey) throws CommonException {
        return dao.read(packetKey);
    }

    @Override
    public void update(Packet packet) throws CommonException {
        //NOT IMPLEMENTED
    }

    @Override
    public void delete(Packet packetKey) throws CommonException {
        dao.delete((String) packetKey.get("id"));
    }

    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {
        return dao.readAll(packet);
    }

    @Override
    public List<TransferObject> find(Packet packet) throws CommonException {
        return dao.find((String) packet.get("findKey"));
    }

    private Prenotazione getEntity(Packet packet) {
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setId((String) packet.get("id"));
        prenotazione.setCliente((String) packet.get("cliente"));
        prenotazione.setAuto((String) packet.get("auto"));
        prenotazione.setAgenzia((String) packet.get("agenzia"));
        prenotazione.setTipo((String) packet.get("tipo"));
        prenotazione.setDataPrenotazione((LocalDate) packet.get("data"));
        LocalDate dataScadenza = (LocalDate) packet.get("scadenza");
        prenotazione.setScadenza(dataScadenza.atTime(Integer.parseInt((String)packet.get("ore")), Integer.parseInt((String)packet.get("minuti"))));
        prenotazione.setAcconto(Double.parseDouble((String)packet.get("acconto")));

        return prenotazione;
    }

    private TransferObject getTO(Prenotazione prenotazione) {
        return prenotazione.getData();
    }
}