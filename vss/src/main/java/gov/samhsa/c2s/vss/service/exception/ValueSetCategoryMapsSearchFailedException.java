package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ValueSetCategoryMapsSearchFailedException extends RuntimeException {
    public ValueSetCategoryMapsSearchFailedException() {
    }

    public ValueSetCategoryMapsSearchFailedException(String message) {
        super(message);
    }

    public ValueSetCategoryMapsSearchFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueSetCategoryMapsSearchFailedException(Throwable cause) {
        super(cause);
    }

    public ValueSetCategoryMapsSearchFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}