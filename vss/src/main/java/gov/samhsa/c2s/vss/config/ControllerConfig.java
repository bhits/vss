package gov.samhsa.c2s.vss.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerConfig {

    // This will set response status to 400 bad request instead of defaulting to 500 internal server error
    @ExceptionHandler({ConstraintViolationException.class , DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Data sent to server")
    public void handleConstraintValidation() {
        // TODO :: Need to give more details on exact violation and the model inforamtion
    }

}
