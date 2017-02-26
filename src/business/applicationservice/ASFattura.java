package business.applicationservice;

import business.applicationservice.validator.Validator;
import business.applicationservice.validator.ValidatorFactory;
import business.entity.Fattura;
import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.exception.CommonException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by antonio on 29/10/15.
 */
public class ASFattura implements ApplicationService {

    private final DataAccessObject dao = DAOFactory.getDAO("DAOFattura");
    private final Validator validator = ValidatorFactory.getValidator("ValidatorFattura");

    @Override
    public void create(Packet packet) throws CommonException {
        Fattura fattura = getEntity(packet);
        validator.validate(fattura);
        TransferObject to = getTO(fattura);

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
        //NOT IMPLEMENTED
    }

    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {
        return dao.readAll(packet);
    }

    @Override
    public List<TransferObject> find(Packet packet) throws CommonException {
        return dao.find((String) packet.get("findKey"));
    }

    private Fattura getEntity(Packet packet) {
        Fattura fattura = new Fattura();

        fattura.setId((String) packet.get("id"));
        fattura.setCliente((String) packet.get("cliente"));
        fattura.setContratto((String) packet.get("contratto"));
        fattura.setAgenzia((String) packet.get("agenzia"));
        fattura.setDataEmissione((LocalDate) packet.get("data"));
        fattura.setImporto((Double) packet.get("importo"));
        fattura.setContratto((String) packet.get("contratto"));
        fattura.setDescrizione((String) packet.get("descrizione"));

        return fattura;
    }

    private TransferObject getTO(Fattura fattura) {
        return fattura.getData();
    }


}
