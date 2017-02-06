package gov.samhsa.c2s.vss.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class SensitivityPolicySearchFailedException extends RuntimeException {
    public SensitivityPolicySearchFailedException() {
    }

    public SensitivityPolicySearchFailedException(String message) {
        super(message);
    }

    public SensitivityPolicySearchFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SensitivityPolicySearchFailedException(Throwable cause) {
        super(cause);
    }

    public SensitivityPolicySearchFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}