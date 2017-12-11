package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.UserWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.ApiException;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/db/user")
@Singleton //We need it to say jersey to use an unique instance
public class UserWorldDBService {
    final static Logger logger = Logger.getLogger(UserWorldDBService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerService(User u) throws ApiException {
        try  {
            UserWorldDBImpl.getInstance().register(u);
            logger.info("registerService: User with username: " + u.getUsername() + " have been created.");
            return Response.status(201).entity(u.getId()).build();
        }
        catch (UserWorldDbException e) {
            logger.warn("registerService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginService(User i) throws ApiException {
        try {
            User o = UserWorldDBImpl.getInstance().login(i);
            if (o != null) {
                logger.info("loginService: User with username: " + i.getUsername() + " have been logged in.");
                return Response.status(200).entity(o).build() ;
            }
            else {
                logger.info("loginService: User with username: " + i.getUsername() + " doesn't exist in db");
                return Response.status(404).entity("404 Not Found").type("text/plain").build() ;
            }

        }
        catch (UserWorldDbException e){
            logger.warn("loginService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }



}
