package business.applicationservice;

import business.applicationservice.validator.Validator;
import business.applicationservice.validator.ValidatorFactory;
import business.entity.Cliente;
import business.to.TransferObject;
import integration.dao.DAOFactory;
import integration.dao.DataAccessObject;
import utility.Packet;
import utility.exception.CommonException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by salvatore on 22/10/15.
 */
public class ASCliente implements ApplicationService {

    private final DataAccessObject dao = DAOFactory.getDAO("DAOCliente");
    private final Validator validator = ValidatorFactory.getValidator("ValidatorCliente");

    @Override
    public void create(Packet packet) throws CommonException {
        Cliente cliente = getEntity(packet);
        validator.validate(cliente);
        TransferObject to = getTO(cliente);

        dao.create(to);
    }

    @Override
    public TransferObject read(Packet packetKey) throws CommonException {
        return dao.read(packetKey);
    }

    @Override
    public void update(Packet packet) throws CommonException {
        Cliente cliente = getEntity(packet);
        validator.validate(cliente);
        TransferObject to = getTO(cliente);

        dao.update(to);
    }

    @Override
    public void delete(Packet packetKey) {
    }


    public List<TransferObject> readAll(Packet packet) throws CommonException {
        return dao.readAll(packet);
    }

    @Override
    public List<TransferObject> find(Packet packet) throws CommonException {
        return dao.find((String) packet.get("findKey"));
    }

    private Cliente getEntity(Packet packet) {
        Cliente cliente = new Cliente();

        cliente.setId((String) packet.get("id"));
        cliente.setNome((String) packet.get("nome"));
        cliente.setCognome((String) packet.get("cognome"));
        cliente.setEmail((String) packet.get("email"));
        cliente.setTelefono((String) packet.get("telefono"));
        cliente.setCitta((String) packet.get("citta"));
        cliente.setIndirizzo((String) packet.get("indirizzo"));
        cliente.setCodiceFiscale((String) packet.get("cf"));
        cliente.setNumeroDocumento((String) packet.get("nrDocumento"));
        cliente.setTipoDocumento((String) packet.get("tipoDocumento"));
        cliente.setNumeroPatente((String) packet.get("nrPatente"));
        cliente.setCartaCredito((String) packet.get("nrCarta"));
        cliente.setIntestatarioCarta((String) packet.get("intCarta"));
        cliente.setDataRilascioPatente((LocalDate) packet.get("dataRilascio"));

        return cliente;
    }

    private TransferObject getTO(Cliente cliente) {
        return cliente.getData();
    }
}
