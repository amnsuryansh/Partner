package com.meet.partner.exception;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.exception.exceptionModel.ErrorMessage;
import org.springframework.http.HttpStatus;

public class PartnerInfoException extends RuntimeException {
    private ErrorMessage errorMessage;

    public PartnerInfoException(String message, InfoType notificationinfo, HttpStatus status) {
        super(message);
        this.errorMessage = new ErrorMessage(message, notificationinfo, status);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
