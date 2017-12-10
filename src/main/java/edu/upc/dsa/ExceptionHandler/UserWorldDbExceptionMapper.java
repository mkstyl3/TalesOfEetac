package edu.upc.dsa.ExceptionHandler;

import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserWorldDbExceptionMapper implements ExceptionMapper<UserWorldDbException> {

    @Override
    public Response toResponse(UserWorldDbException e) {
        return Response.status(400).entity(Exceptions.getStackTraceAsString(e)).type("text/plain")
                .build();
    }
}
