package integration.connector;

import utility.exception.CommonException;

import java.sql.ResultSet;

public interface Connector {

    ResultSet executeQuery(String query) throws CommonException;

    void executeUpdate(String query) throws CommonException;

    void openConnection() throws CommonException;

    void closeConnection();

}
