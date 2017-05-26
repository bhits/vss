package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodeSystemVersionNotFoundException extends RuntimeException {
    public CodeSystemVersionNotFoundException() {
    }

    public CodeSystemVersionNotFoundException(String message) {
        super(message);
    }

    public CodeSystemVersionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeSystemVersionNotFoundException(Throwable cause) {
        super(cause);
    }

    public CodeSystemVersionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}