package edu.upc.dsa.ExceptionHandler;

import javax.ws.rs.core.Response;

public class DAOException extends Exception {

    private Response response;

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    Response getResponse() {
        return response;
    }
}
