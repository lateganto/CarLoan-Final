package integration.dao;


import utility.dialog.Dialog;

public class DAOFactory {
    private static final String DAO_PATH = "integration.dao.";

    private DAOFactory() {

    }

    public static DataAccessObject getDAO(String daoName) {
        String daoCanonicalName = DAO_PATH + daoName;
        DataAccessObject daoInstance = null;

        try {
            Class<?> daoClass = Class.forName(daoCanonicalName);
            daoInstance = (DataAccessObject) daoClass.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e ) {

            Dialog.showErrorDialog("Errore imprevisto");

            //TODO logger
            /**/e.printStackTrace();
        }

        return daoInstance;
    }

}
