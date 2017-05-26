package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodeSystemNotFoundException extends RuntimeException {
    public CodeSystemNotFoundException() {
    }

    public CodeSystemNotFoundException(String message) {
        super(message);
    }

    public CodeSystemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeSystemNotFoundException(Throwable cause) {
        super(cause);
    }

    public CodeSystemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}