package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Controller.API.UserWorldDBImpl;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
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
    public Response registerService(User u) throws UserWorldDbException {
        Response r;
        try  {
            UserWorldDBImpl.getInstance().register(u);
            logger.info("createUserService: User with username: " + u.getUsername() + " have been created.");
            r = Response.status(201).entity(u.getId()).build();
        }

        catch (UserWorldDbException e) {
            r = Response.status(400).entity(u.getId()).build();
            logger.info("createUserService: User with username: " + u.getUsername() + " have been created.");
            throw new UserWorldDbException(e);
        }

        return r;
    }



}
