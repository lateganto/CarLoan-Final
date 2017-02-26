package business.applicationservice.validator;

import business.entity.Entity;
import business.entity.Fattura;
import utility.exception.CommonException;
import utility.exception.InvalidFatturaFieldException;

import java.time.LocalDate;

/**
 * Created by antonio on 29/10/15.
 */
public class ValidatorFattura implements Validator {

    private Fattura entity;

    public ValidatorFattura() {

    }

    @Override
    public void validate(Entity entity) throws CommonException {
        this.entity = (Fattura) entity;

        validateDataEmissione();

    }

    private void validateDataEmissione() throws InvalidFatturaFieldException {
        LocalDate date = entity.getDataEmissione();

        if(!date.equals(LocalDate.now())) {
            throw new InvalidFatturaFieldException("La data della fattura deve essere quella odierna");
        }
    }

}
