package edu.upc.dsa.View.ExceptionHandling;

public class UserWorldDbException extends Exception {
    public UserWorldDbException (String message, Throwable cause) {
        super(message, cause);
    }
}
