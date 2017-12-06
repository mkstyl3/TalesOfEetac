package edu.upc.dsa.View.ExceptionHandling;

import javax.ws.rs.core.Response;

public class DAOUserException extends Exception {
    public DAOUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public Response getResponse() {
        return response;
    }
}
