package com.loopcreative.web.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Message {

    private HttpStatus status;
    private String message;
    private Object data;

    public Message() {
        this.status = HttpStatus.OK;
        this.message = "success";
    }

    public Message(Object obejct) {
        this.status = HttpStatus.OK;
        this.data = obejct;
        this.message = "success";
    }

    public static Message getMessage(Object object, HttpStatus httpStatus){
        Message message = new Message();
        message.setMessage("success");
        message.setData(object);
        message.setStatus(httpStatus);

        return message;
    }
}
