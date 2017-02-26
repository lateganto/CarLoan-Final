package business.applicationservice.validator;

import utility.dialog.Dialog;

/**
 * Created by salvatore on 23/10/15.
 */
public class ValidatorFactory {

    private static final String DAO_PATH = "business.applicationservice.validator.";

    private ValidatorFactory() {

    }

    public static Validator getValidator(String validatorName) {
        String validatorCanonicalName = DAO_PATH + validatorName;
        Validator validatorInstance = null;

        try {
            Class<?> validatorClass = Class.forName(validatorCanonicalName);
            validatorInstance = (Validator) validatorClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e ) {

            Dialog.showErrorDialog("Errore imprevisto");

            /**/e.printStackTrace();
        }

        return validatorInstance;
    }

}
