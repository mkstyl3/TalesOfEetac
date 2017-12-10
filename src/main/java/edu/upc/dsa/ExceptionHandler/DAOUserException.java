package edu.upc.dsa.ExceptionHandler;

import javax.ws.rs.core.Response;

public class DAOUserException extends Exception {



    public DAOUserException(Throwable cause) {
        super(cause);
    }
}
