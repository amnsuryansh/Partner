package com.meet.partner.messageConfig;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.meet.partner.codetype.InfoType;
import com.meet.partner.exception.exceptionModel.Message;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({ "data", "message", "infoType", "status", "statusCode"})
public class ResponseMessage extends Message {

    private Object data;

    private ResponseMessage() {
    }

    public ResponseMessage(Object data, String message, InfoType infoType, HttpStatus status) {
        super(message, infoType, status);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
