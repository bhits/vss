package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValueSetCategoryNotFoundException extends RuntimeException {
    public ValueSetCategoryNotFoundException() {
    }

    public ValueSetCategoryNotFoundException(String message) {
        super(message);
    }

    public ValueSetCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueSetCategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public ValueSetCategoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}