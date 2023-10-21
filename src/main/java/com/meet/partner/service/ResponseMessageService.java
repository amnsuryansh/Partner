package com.meet.partner.service;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.messageConfig.ErrorDisplayConfig;
import com.meet.partner.exception.exceptionModel.ErrorMessage;
import com.meet.partner.messageConfig.ResponseMessage;
import com.meet.partner.messageConfig.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseMessageService {

    @Autowired
    private ResponseMessages responseMessages;

    @Autowired
    private ErrorDisplayConfig errorDisplayConfig;

    public ResponseMessage generateResponseMessage(Object data, String messageKey, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey), InfoType.INFO, status);
    }

    public ResponseMessage generateResponseMessage(Object data, String messageKey, List<Object> args, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey, args), InfoType.INFO, status);
    }

    public ResponseMessage generateWarningMessage(Object data,String messageKey, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey), InfoType.WARNING , status);
    }

    public ErrorMessage generateErrorMessage(Object data, String messageKey, HttpStatus status) {
        return new ErrorMessage(data,errorDisplayConfig.getProperty(messageKey), InfoType.ERROR , status);
    }

    public ErrorMessage generateErrorMessage(Object data, String messageKey, List<Object> args, HttpStatus status) {
        return new ErrorMessage(data,errorDisplayConfig.getProperty(messageKey,args), InfoType.ERROR , status);
    }

}
