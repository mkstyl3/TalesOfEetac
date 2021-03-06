package edu.upc.dsa.ExceptionHandler;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException ex) {
        return Response.status(400).entity(Exceptions.getStackTraceAsString(ex)).type("text/plain")
                .build();
    }
}
