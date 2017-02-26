package business.applicationservice.validator;

import business.entity.Cliente;
import business.entity.Entity;
import utility.exception.CommonException;
import utility.exception.InvalidClienteFieldException;

/**
 * Created by salvatore on 23/10/15.
 */
public class ValidatorCliente implements Validator {

    //TODO CONTROLLO SU DATA NASCITA: DEVE ESSERE 18+

    //TODO da controllare
    private static final String REGEX_DOCUMENT_DRIVINGLICENSE = "[A-Za-z0-9]*";
    private static final String REGEX_CF =
            "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$";
    private static final String REGEX_TELEPHONE_CREDITCARD = "[0-9]*";
    private static final String REGEX_MAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\." +
            "[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final int DOCUMENT_LENGTH = 9;
    private static final int DRIVING_LICENSE_LENGTH = 12;
    private static final int CREDITCARD_LENGTH = 16;
    private static final int NAME_SURNAME_CITY_MAIL_MAX = 30;
    private static final int ADDRESS_MAX = 40;
    private static final int TELOPHONE_MIN = 7;
    private static final int TELOPHONE_MAX = 13;
    private static final int MAIL_MIN = 6;

    private Cliente entity = null;
    private boolean validated;

    public ValidatorCliente() {

    }

    @Override
    public void validate(Entity entity) throws CommonException {
        this.entity = (Cliente) entity;
        validateNameSurname();
        validateCF();
        validateDocument();
        validateCity();
        validateAddress();
        validateTelephone();
        validateEmail();
        validateDrivingLicense();

        //TODO controllo data
        validateCreditCard();
        validateCardOwner();
    }

    private void validateNameSurname() throws InvalidClienteFieldException {
        String name = entity.getNome();
        String surname = entity.getCognome();

        validated = ((name.length() >= 1 && name.length() <= NAME_SURNAME_CITY_MAIL_MAX) &&
                (surname.length() >= 1 && surname.length()<= NAME_SURNAME_CITY_MAIL_MAX));

        if (!validated) {
            throw new InvalidClienteFieldException("Nome e Cognome devono avere lunghezza compresa tra 1 e "
                    + NAME_SURNAME_CITY_MAIL_MAX + " caratteri");
        }
    }

    private void validateCF() throws InvalidClienteFieldException {
        String cf = entity.getCodiceFiscale();

        validated = (cf.matches(REGEX_CF));

        if (!validated) {
            throw new InvalidClienteFieldException("Formato codice fiscale non valido");
        }
    }

    private void validateDocument() throws InvalidClienteFieldException {
        String document = entity.getNumeroDocumento();

        if(!(document.length() == DOCUMENT_LENGTH)) {
            throw new InvalidClienteFieldException("Lunghezza numero Documento non valida");
        }

        if (!(document.matches(REGEX_DOCUMENT_DRIVINGLICENSE))) {
            throw new InvalidClienteFieldException("Formato numero Documento non valido");
        }
    }

    private void validateCity() throws InvalidClienteFieldException {
        String city = entity.getCitta();

        validated = ((city.length() >= 2 && city.length() <= NAME_SURNAME_CITY_MAIL_MAX));

        if (!validated) {
            throw new InvalidClienteFieldException("Il campo citta deve avere lunghezza compresa tra 2 e "
                    + NAME_SURNAME_CITY_MAIL_MAX + " caratteri");
        }
    }

    private void validateAddress() throws InvalidClienteFieldException {
        String address = entity.getIndirizzo();

        validated = ((address.length() >= 4 && address.length() <= NAME_SURNAME_CITY_MAIL_MAX));

        if (!validated) {
            throw new InvalidClienteFieldException("Il campo indirizzo deve avere lunghezza compresa tra 4 e "
                    + ADDRESS_MAX + " caratteri");
        }
    }

    private void validateTelephone() throws InvalidClienteFieldException {
        String telephone = entity.getTelefono();

        if(!(telephone.length() >= TELOPHONE_MIN && telephone.length() <= TELOPHONE_MAX)) {
            throw new InvalidClienteFieldException("Lunghezza numero Telefono non valida");
        }

        if (!(telephone.matches(REGEX_TELEPHONE_CREDITCARD))) {
            throw new InvalidClienteFieldException("Formato numero Telefono non valido");
        }
    }

    private void validateEmail() throws InvalidClienteFieldException {
        String email = entity.getEmail();

        validated = ((email.length() >= MAIL_MIN && email.length() <= NAME_SURNAME_CITY_MAIL_MAX)
                && email.matches(REGEX_MAIL));

        if (!validated) {
            throw new InvalidClienteFieldException("Formato Email non valido");
        }
    }

    private void validateDrivingLicense() throws InvalidClienteFieldException {
        String drivingLicense = entity.getNumeroPatente();

        if (drivingLicense.length() != DRIVING_LICENSE_LENGTH) {
            throw new InvalidClienteFieldException("Lunghezza numero Patente non valida");
        }

        if (!(drivingLicense.matches(REGEX_DOCUMENT_DRIVINGLICENSE))) {
            throw new InvalidClienteFieldException("Formato numero Patente non valido");
        }
    }

    private void validateCreditCard() throws InvalidClienteFieldException {
        String creditCard = entity.getCartaCredito();

        if (creditCard.length() != CREDITCARD_LENGTH) {
            throw new InvalidClienteFieldException("Lunghezza numero Carta di credito non valida");
        }

        if (!(creditCard.matches(REGEX_TELEPHONE_CREDITCARD))) {
            throw new InvalidClienteFieldException("Formato numero Carta di credito non valido");
        }
    }

    private void validateCardOwner() throws InvalidClienteFieldException {
        String cardOwner = entity.getIntestatarioCarta();

        if (!((cardOwner.length() >= 5 && cardOwner.length() <= NAME_SURNAME_CITY_MAIL_MAX))) {
            throw new InvalidClienteFieldException("Nome e Cognome intestatario carta devono avere lunghezza" +
                    "complessiva tra 5 e " + NAME_SURNAME_CITY_MAIL_MAX + " caratteri");
        }
    }

}
