package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValueSetNotFoundException extends RuntimeException {
    public ValueSetNotFoundException() {
    }

    public ValueSetNotFoundException(String message) {
        super(message);
    }

    public ValueSetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueSetNotFoundException(Throwable cause) {
        super(cause);
    }

    public ValueSetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}