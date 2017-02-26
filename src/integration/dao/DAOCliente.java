package integration.dao;

import business.to.TOCliente;
import business.to.TOFactory;
import business.to.TransferObject;
import integration.connector.Connector;
import integration.connector.ConnectorFactory;
import utility.Packet;
import utility.exception.CommonException;
import utility.reader.XMLReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 14/10/15.
 */
public class DAOCliente implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "cliente";
    private final String REGEX;

    public DAOCliente() {
        connector = ConnectorFactory.getConnector();
        xmlReader = XMLReader.getReader("query");
        REGEX = xmlReader.getRegexValue();
    }

    @Override
    public void create(TransferObject data) throws CommonException {
        connector.openConnection();

        List<Object> attributes = data.getAttributes();
        String query = xmlReader.getQueryValue(QUERY_ID, "insert");

        for (Object object:attributes) {
            query = query.replaceFirst(REGEX, object.toString());
        }

        connector.executeUpdate(query);

        connector.closeConnection();

    }

    @Override
    public TransferObject read(Packet packet) throws CommonException {
        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "read");
        query = query.replaceFirst(REGEX, packet.get("id").toString());

        ResultSet resultSet = connector.executeQuery(query);
        TransferObject to = readListFromRs(resultSet).get(0);

        //TODO vedere gestione connessione
        connector.closeConnection();

        return to;
    }

    @Override
    public void update(TransferObject data) throws CommonException {
        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "update");
        List<Object> toList = data.getAttributes();

        for (Object o:toList) {
            query = query.replaceFirst(REGEX, o.toString());
        }
        query = query.replaceFirst(REGEX, data.getId());

        connector.executeUpdate(query);

        connector.closeConnection();

    }

    @Override
    public void delete(String id) throws CommonException {
        //NOT IMPLEMENTED
    }

    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {
        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "readall");
        ResultSet resultSet = connector.executeQuery(query);

        List<TransferObject> toList = readListFromRs(resultSet);

        connector.closeConnection();

        return toList;
    }

    @Override
    public List<TransferObject> find(String mainInfo) throws CommonException {
        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "find");
        query = query.replaceAll(REGEX, mainInfo);

        ResultSet resultSet = connector.executeQuery(query);
        List<TransferObject> toList = readListFromRs(resultSet);

        connector.closeConnection();

        return toList;
    }

    private List<TransferObject> readListFromRs(ResultSet rs) {
        List<TransferObject> list = new LinkedList<TransferObject>();

        try {
            while (rs.next()) {
                TOCliente cliente = (TOCliente) TOFactory.getTO("TOCliente");

                cliente.setId(rs.getString("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCitta(rs.getString("citta"));
                cliente.setIndirizzo(rs.getString("indirizzo"));
                cliente.setCodiceFiscale(rs.getString("codice_fiscale"));
                cliente.setNumeroDocumento(rs.getString("numero_documento"));
                cliente.setTipoDocumento(rs.getString("tipo_documento"));
                cliente.setNumeroPatente(rs.getString("numero_patente"));
                cliente.setDataRilascioPatente(LocalDate.parse(rs.getString("data_rilascio_patente")));
                cliente.setCartaCredito(rs.getString("carta_credito"));
                cliente.setIntestatarioCarta(rs.getString("intestatario_carta"));

                list.add(cliente);
            }

        } catch (SQLException e) {

            //TODO gestire eccezione
            e.printStackTrace();
        }

        //TODO verificare correttezza chiusura qui
        try {
            rs.close();
        } catch (SQLException e) {

            //TODO gestire eccezione
            e.printStackTrace();
        }

        return list;
    }
}
