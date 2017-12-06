package edu.upc.dsa.View.ExceptionHandling;

public class DAOUserException extends Exception {
    public DAOUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
