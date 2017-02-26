package business.applicationservice.validator;

import business.entity.Entity;
import utility.exception.CommonException;

/**
 * Created by salvatore on 23/10/15.
 */
public interface Validator {

    void validate(Entity entity) throws CommonException;
}
