package edu.upc.dsa.View;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Model.User;
import edu.upc.dsa.View.ExceptionHandling.DAOUserException;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/db/user")
@Singleton //We need it to say jersey to use an unique instance
public class UserWorldDBService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUserService(User u) {
        try  {
            DAOImpl.getInstance().insertUser(u);
        }
        catch (DAOUserException e) {
            e.printStackTrace();
        }

    }
}
