package edu.upc.dsa.View.ExceptionHandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class Exceptions {

    // -- Exceptions
    public static class MyException extends RuntimeException {

        private Response response;

        public MyException(Response response) {
            this.response = response;
        }

        public Response getResponse() {
            return response;
        }
    }

    public static class MySubException extends MyException {

        public MySubException(Response response) {
            super(response);
        }
    }

    public static class MySubSubException extends MySubException {

        public MySubSubException(Response response) {
            super(response);
        }
    }

    // -- Exception Mappers
    @Provider
    public static class MyExceptionMapper implements ExceptionMapper<DAOUserException> {

        @Override
        public Response toResponse(DAOUserException exception) {
            Response r = exception.getResponse();
            return Response.status(r.getStatus()).entity(
                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
        }
    }

    @Provider
    public static class MySubExceptionMapper implements ExceptionMapper<MySubException> {

        @Override
        public Response toResponse(MySubException exception) {
            Response r = exception.getResponse();
            return Response.status(r.getStatus()).entity(
                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
        }
    }
}
