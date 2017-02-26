package integration.dao;

import business.to.TODitta;
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
public class DAODitta implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "ditta";
    private final String REGEX;

    public DAODitta() {
        connector = ConnectorFactory.getConnector();
        xmlReader = XMLReader.getReader("query");
        REGEX = xmlReader.getRegexValue();
    }

    @Override
    public void create(TransferObject data) throws CommonException {
        //NOT IMPLEMENTED
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
        //NOT IMPLEMENTED
        return null;
    }

    @Override
    public List<TransferObject> find(String mainInfo) throws CommonException {
        //NOT IMPLEMENTED
        return null;
    }

    private List<TransferObject> readListFromRs(ResultSet rs) {
        List<TransferObject> list = new LinkedList<TransferObject>();

        try {
            while (rs.next()) {
                TODitta ditta = (TODitta) TOFactory.getTO("TODitta");

                ditta.setId(rs.getString("partita_iva"));
                ditta.setRagioneSociale(rs.getString("ragione_sociale"));
                ditta.setCitta(rs.getString("citta"));
                ditta.setIndirizzo(rs.getString("indirizzo"));
                ditta.setSitoWeb(rs.getString("sito_web"));
                ditta.setEmail(rs.getString("email"));

                list.add(ditta);
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
