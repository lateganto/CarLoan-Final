package business.applicationservice.validator;

import business.entity.Entity;
import business.entity.Prenotazione;
import utility.exception.CommonException;
import utility.exception.InvalidPrenotazioneFieldException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;

/**
 * Created by antonio on 28/10/15.
 */
public class ValidatorPrenotazione implements Validator {

    private Prenotazione entity;

    public ValidatorPrenotazione() {

    }

    @Override
    public void validate(Entity entity) throws CommonException {
        this.entity = (Prenotazione) entity;

        validateDate();
        validateScadenza();
    }

    private void validateDate() throws InvalidPrenotazioneFieldException {
        LocalDate date = entity.getDataPrenotazione();

        if(!date.equals(LocalDate.now())) {
            throw new InvalidPrenotazioneFieldException("La data di prenotazione deve essere quella odierna");
        }
    }

    private void validateScadenza() throws InvalidPrenotazioneFieldException {
        LocalDate date = entity.getDataPrenotazione();
        LocalDateTime scadenza = entity.getScadenza();

        if(date.isAfter(ChronoLocalDate.from(scadenza))){
            throw new InvalidPrenotazioneFieldException("La data di scadenza deve essere successiva a quella di prenotazione");
        }
    }

}
