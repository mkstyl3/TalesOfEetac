package edu.upc.dsa.Controller.ExceptionHandler;

public class ApiException extends UserWorldDbException {
    public ApiException(Throwable cause) {
        super(cause);
    }
}
