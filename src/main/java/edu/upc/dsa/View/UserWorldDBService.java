package edu.upc.dsa.View;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Controller.UserWorldDB;
import edu.upc.dsa.Controller.UserWorldDBImpl;
import edu.upc.dsa.Controller.UserWorldImpl;
import edu.upc.dsa.Model.User;
import edu.upc.dsa.View.ExceptionHandling.DAOUserException;
import edu.upc.dsa.View.ExceptionHandling.UserWorldDbException;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Path("/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createService(User u) {
        try  {
            DAOImpl.getInstance().insertUser(u);
            logger.info("createUserService: User with username: "+u.getUsername()+" have been created.");
        }
        catch (DAOUserException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean registerService(User u) {
        boolean successful = false;
        try  {
            if (UserWorldDBImpl.getInstance().register(u) != null) {
                successful = true;
                logger.info("createUserService: User with username: " + u.getUsername() + " have been created.");
            }
            else {
                logger.info("createUserService: User with username: "+u.getUsername()+" already exist.");
            }
        } catch (UserWorldDbException e) {
            e.printStackTrace();
        }
        return successful;
    }

}
