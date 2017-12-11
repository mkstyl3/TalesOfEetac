package edu.upc.dsa.ExceptionHandler;

public class Exceptions {
/*
    // -- Exceptions
    public static class ReflectionException2 extends  {

        private Response response;

        public ReflectionException2(Response response) {
            this.response = response;
        }

        public Response getResponse() {
            return response;
        }
    }

    public static class MySubException extends ReflectionException2 {

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
    public static class MyExceptionMapper implements ExceptionMapper<ReflectionException2> {

        @Override
        public Response toResponse(ReflectionException2 e) {
            Response r = e.getResponse();
            return Response.status(r.getStatus()).entity(
                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
        }
    }

    @Provider
    public static class MySubExceptionMapper implements ExceptionMapper<MySubException> {

        @Override
        public Response toResponse(MySubException e) {
            Response r = e.getResponse();
            return Response.status(r.getStatus()).entity(
                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
        }
    }*/
}
