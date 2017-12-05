package edu.upc.dsa.View;

import edu.upc.dsa.Controller.GameDB.DAO.DAO;
import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Controller.UserWorldImpl;
import edu.upc.dsa.Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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
    public boolean createUserService(User u) throws IllegalAccessException, NoSuchFieldException, SQLException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        DAOImpl.getInstance().insert(u);
        return true;
    }

}
