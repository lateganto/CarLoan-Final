package integration.dao;

import business.to.TOFactory;
import business.to.TOUtente;
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


public class DAOUtente implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "utente";
    private final String REGEX;

    public DAOUtente() {
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

        String query;

        if (packet.get("criterion").equals("login")) {

            query = xmlReader.getQueryValue(QUERY_ID, "login");

            query = query.replaceFirst(REGEX, packet.get("username").toString());
            query = query.replaceFirst(REGEX, packet.get("password").toString());

        } else {

            query = xmlReader.getQueryValue(QUERY_ID, "read");
            query = query.replaceFirst(REGEX, (String) packet.get("id"));

        }

        ResultSet resultSet = connector.executeQuery(query);
        List<TransferObject> list = readListFromRs(resultSet);

        TransferObject to = null;

        if (!list.isEmpty()) {
            to = list.get(0);
        }

        //TODO vedere gestione connessione
        connector.closeConnection();

        return to;
    }

    @Override
    public void update(TransferObject data) throws CommonException {

        String query = xmlReader.getQueryValue(QUERY_ID, "update");
        //TODO da verificare secondo parametro funzione
        query = query.replaceFirst(REGEX, (String) data.getAttributes().get(1));
        query = query.replaceFirst(REGEX, data.getId());

        //TODO possibile eccezione
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

        connector.openConnection();

        String query = xmlReader.getQueryValue(QUERY_ID, "find");
        query = query.replaceFirst(REGEX, mainInfo);

        ResultSet resultSet = connector.executeQuery(query);
        List<TransferObject> toList = readListFromRs(resultSet);

        connector.closeConnection();

        return toList;
    }

    //TODO implementare ricerca e altri metodi

    private List<TransferObject> readListFromRs(ResultSet rs) {
        List<TransferObject> list = new LinkedList<>();

        try {
            while (rs.next()) {
                TOUtente utente = (TOUtente) TOFactory.getTO("TOUtente");

                utente.setId(rs.getString("id_utente"));
                utente.setUsername(rs.getString("username"));
                utente.setTipo(rs.getString("tipo"));

                list.add(utente);
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
