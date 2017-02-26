package integration.connector;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import utility.exception.MySQLConnectionException;
import utility.exception.MySQLQueryException;
import utility.exception.MySQLUpdateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class MySQLConnector implements Connector {
    private static MySQLConnector connectorInstance = null;

    private static final String DB_PROPERTIES = "config/DBConnection.properties";
    private Connection connection = null;
    private final Properties dbProperties;

    private MySQLConnector() {
        dbProperties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(DB_PROPERTIES);
            dbProperties.load(inputStream);

        } catch (IOException e) {

            /**/e.printStackTrace();
            //throw new MySQLConnectionException("Impossibile connettersi al Database");

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {

                //TODO mettere logger
                /**/e.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws MySQLQueryException {
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {

            /**/e.printStackTrace();
            throw new MySQLQueryException("Errore durante la lettura delle informazioni dal Database");
        }

        return resultSet;
    }

    @Override
    public void executeUpdate(String query) throws MySQLUpdateException {

        PreparedStatement statement;
        boolean result = false;

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            result = true;
        } catch (SQLIntegrityConstraintViolationException e) {

            /**/e.printStackTrace();
            throw new MySQLUpdateException("Elemento gia' presente nel Database");

        } catch (SQLException e) {

            /**/e.printStackTrace();
            throw new MySQLUpdateException("Errore durante l'aggiornamento sul Database");
        }

    }


    @Override
    public void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {

            //TODO gestire eccezione con logger
            /**/e.printStackTrace();
        }
    }

    @Override
    public void openConnection() throws MySQLConnectionException {

        try {

            MysqlDataSource mysqlDataSource = new MysqlDataSource();

            mysqlDataSource.setServerName(dbProperties.getProperty("MYSQL_SERVER"));
            mysqlDataSource.setPort(Integer.parseInt(dbProperties.getProperty("MYSQL_PORT")));
            mysqlDataSource.setDatabaseName(dbProperties.getProperty("MYSQL_DB"));

            connection = mysqlDataSource.getConnection(dbProperties.getProperty("MYSQL_USERNAME"),
                    dbProperties.getProperty("MYSQL_PASSWORD"));

        } catch (SQLException e) {

            //TODO mettere tutti i messaggi di errore in unico file
            /**/e.printStackTrace();
            throw new MySQLConnectionException("Impossibile connettersi al Database");
        }

    }

    public static MySQLConnector getConnectorInstance() {
        if (connectorInstance == null) {
            connectorInstance = new MySQLConnector();
        }

        return connectorInstance;
    }
}
