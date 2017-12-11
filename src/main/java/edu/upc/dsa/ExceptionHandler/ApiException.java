package edu.upc.dsa.ExceptionHandler;

public class ApiException extends UserWorldDbException {
    public ApiException(Throwable cause) {
        super(cause);
    }
}
