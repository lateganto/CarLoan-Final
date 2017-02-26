package business.to;

public class TOFactory {

    private static final String DAO_PATH = "business.to.";

    private TOFactory() {

    }

    public static TransferObject getTO(String toName) {
        String daoCanonicalName = DAO_PATH + toName;
        TransferObject toInstance = null;

        try {
            Class<?> toClass = Class.forName(daoCanonicalName);
            toInstance = (TransferObject) toClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        return toInstance;
    }


}
