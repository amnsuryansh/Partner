package com.meet.partner.exception.service;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.messageConfig.ErrorDisplayConfig;
import com.meet.partner.exception.PartnerInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerExceptionComponent {

    @Autowired
    private ErrorDisplayConfig messageProperties;

    public void generateException(String messageKey, InfoType notificationinfo, HttpStatus status) {
        throw new PartnerInfoException(messageProperties.getProperty(messageKey), notificationinfo, status);
    }

    public void generateException(String messageKey, List<Object> args, InfoType notificationinfo,
                                  HttpStatus status) {
        throw new PartnerInfoException(messageProperties.getProperty(messageKey, args), notificationinfo, status);
    }
}
