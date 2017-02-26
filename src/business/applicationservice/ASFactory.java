package business.applicationservice;

/**
 * Created by salvatore on 06/10/15.
 */
public class ASFactory {

    private static final String AS_PATH = "business.applicationservice.";

    private ASFactory() {

    }

    public static ApplicationService getAS(String as) {
        String daoCanonicalName = AS_PATH + as;
        ApplicationService appServiceInstance = null;

        try {
            Class<?> appServiceClass = Class.forName(daoCanonicalName);
            appServiceInstance = (ApplicationService) appServiceClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

            //TODO gestire eccezione
            e.printStackTrace();
        }

        return appServiceInstance;
    }


}
