package integration.dao;

import business.to.TOContratto;
import business.to.TOFactory;
import business.to.TransferObject;
import integration.connector.Connector;
import integration.connector.ConnectorFactory;
import utility.Packet;
import utility.exception.CommonException;
import utility.reader.XMLReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 14/10/15.
 */
public class DAOContratto implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "contratto";
    private final String REGEX;

    DAOContratto() {
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
                TOContratto contratto = (TOContratto) TOFactory.getTO("TOContratto");


                contratto.setId(rs.getString("numero_contratto"));
                contratto.setAgenzia(rs.getString("agenzia"));
                contratto.setAuto(rs.getString("auto"));
                //contratto.setOptional(rs.getArray("optional")); //TODO vedere passaggio dell'array di optional
                contratto.setDataInizio(LocalDateTime.parse(rs.getString("dataInizio")));
                contratto.setDataFine(LocalDateTime.parse(rs.getString("dataFine")));
                contratto.setCliente(rs.getString("cliente"));
                contratto.setAgenzia(rs.getString("agenzia"));
                contratto.setChilometraggio(rs.getString("chilometraggio"));
                contratto.setChilometriPercorsi(rs.getInt("chilometriPercorsi"));
                //TODO da testare
                contratto.setTariffaBase(rs.getDouble("tariffaBase"));
                contratto.setTariffaFinale(rs.getDouble("tariffaFinale"));
                contratto.setStato(rs.getString("stato"));
                contratto.setChilometriPrevisti(rs.getInt("chilometriPrevisti"));

                list.add(contratto);
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
