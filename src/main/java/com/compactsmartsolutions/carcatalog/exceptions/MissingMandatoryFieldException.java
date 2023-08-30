package com.compactsmartsolutions.carcatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingMandatoryFieldException extends RuntimeException {

    public MissingMandatoryFieldException(String message) {
        super(message);
    }

}
