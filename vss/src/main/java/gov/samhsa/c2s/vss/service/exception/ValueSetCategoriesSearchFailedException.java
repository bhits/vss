package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ValueSetCategoriesSearchFailedException extends RuntimeException {
    public ValueSetCategoriesSearchFailedException() {
    }

    public ValueSetCategoriesSearchFailedException(String message) {
        super(message);
    }

    public ValueSetCategoriesSearchFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueSetCategoriesSearchFailedException(Throwable cause) {
        super(cause);
    }

    public ValueSetCategoriesSearchFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}