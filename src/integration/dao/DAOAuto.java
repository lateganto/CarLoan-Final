package integration.dao;

import business.to.TOAuto;
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
public class DAOAuto implements DataAccessObject {

    private final Connector connector;
    private final XMLReader xmlReader;

    private final String QUERY_ID = "auto";
    private final String REGEX;

    public DAOAuto() {
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

    @Override
    public List<TransferObject> readAll(Packet packet) throws CommonException {
        connector.openConnection();

        String query;
        List<TransferObject> toList;

        if (packet.get("criterion").equals("agenzia")) {
            String idAgenzia = (String) packet.get("agenzia");
            String stato = (String) packet.get("stato");

            query = xmlReader.getQueryValue(QUERY_ID, "readall_agenzia");

            query = query.replaceFirst(REGEX, idAgenzia);
            query = query.replaceFirst(REGEX, stato);

            ResultSet resultSet = connector.executeQuery(query);
            toList = readListFromRs(resultSet);

        } else {
            query = xmlReader.getQueryValue(QUERY_ID, "readall");

            ResultSet resultSet = connector.executeQuery(query);
            toList = readListFromRs(resultSet);
        }

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
                TOAuto auto = (TOAuto) TOFactory.getTO("TOAuto");

                auto.setId(rs.getString("id_auto"));
                auto.setTarga(rs.getString("targa"));
                auto.setModello(rs.getString("marca"));
                auto.setModello(rs.getString("modello"));
                auto.setClasseEmissioni(Integer.parseInt(rs.getString("classe_emissioni")));
                auto.setScadenzaPolizza(rs.getDate("scadenza_polizza"));
                auto.setScadenzaRevisione(rs.getDate("scadenza_revisione"));
                auto.setStato(rs.getString("stato"));
                auto.setCilindrata(rs.getInt("cilindrata"));
                auto.setNumeroPorte(rs.getInt("numero_porte"));
                auto.setPotenza(rs.getInt("potenza"));
                auto.setAlimentazione(rs.getString("alimentazione"));
                auto.setNumeroPorte(rs.getInt("numero_posti"));
                auto.setFascia(rs.getString("fascia"));
                auto.setAgenzia(rs.getString("agenzia"));
                auto.setNote(rs.getString("note"));
                auto.setCambio(rs.getString("cambio"));
                auto.setChilometri(rs.getInt("chilometri"));
                auto.setCarburante(rs.getString("carburante"));


                list.add(auto);
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

    public static void main(String arg[]) {
        DAOAdminAgenzia aa = new DAOAdminAgenzia();
        try {
            aa.find("prova");
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }
}
