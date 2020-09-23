package com.diegomota.curso.ws.services.exception;

import java.io.Serializable;

public class ObjectNotEnableException extends RuntimeException implements Serializable  {

    private static final long serialVersionUID = 1L;

    public ObjectNotEnableException(String message) {
        super(message);
    }
}
