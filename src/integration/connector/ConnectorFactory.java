package integration.connector;


public class ConnectorFactory {

    private ConnectorFactory() {

    }

    public static Connector getConnector() {
        return MySQLConnector.getConnectorInstance();
    }

}
