package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodedConceptNotFoundException extends RuntimeException {
    public CodedConceptNotFoundException() {
    }

    public CodedConceptNotFoundException(String message) {
        super(message);
    }

    public CodedConceptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodedConceptNotFoundException(Throwable cause) {
        super(cause);
    }

    public CodedConceptNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}