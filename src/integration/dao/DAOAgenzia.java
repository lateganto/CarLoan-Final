package integration.dao;

import business.to.TOAgenzia;
import business.to.TOFactory;
import business.to.TransferObject;
import integration.connector.Connector;
import integration.connector.ConnectorFactory;
import utility.Packet;
import utility.exception.CommonException;
import utility.reader.XMLReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 14/10/15.
 */
public class DAOAgenzia implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "agenzia";
    private final String REGEX;

    public DAOAgenzia() {
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
        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "delete");
        query = query.replaceFirst(REGEX, id);

        connector.executeUpdate(query);

        connector.closeConnection();
    }

    //TODO da cambiare segnatura interfaccia
    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {

        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "readall");

        ResultSet resultSet = connector.executeQuery(query);
        List<TransferObject> toList = readListFromRs(resultSet);

        //TODO vedere gestione connessione
        connector.closeConnection();

        return toList;
    }

    @Override
    public List<TransferObject> find(String mainInfo) throws CommonException {
        return null;
    }

    private List<TransferObject> readListFromRs(ResultSet rs) {
        List<TransferObject> list = new LinkedList<TransferObject>();

        try {
            while (rs.next()) {
                TOAgenzia agenzia = (TOAgenzia) TOFactory.getTO("TOAgenzia");

                agenzia.setId(rs.getString("id_agenzia"));
                agenzia.setCitta(rs.getString("citta"));
                agenzia.setIndirizzo(rs.getString("indirizzo"));
                agenzia.setEmail(rs.getString("email"));
                agenzia.setTelefono(rs.getString("telefono"));

                list.add(agenzia);
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
