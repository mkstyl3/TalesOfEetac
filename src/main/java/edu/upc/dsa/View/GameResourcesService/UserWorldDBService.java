package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.UserItemWorldDBImpl;
import edu.upc.dsa.Controller.API.UserWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.*;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.Model.Relation.UserItem;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
            logger.info("registerService: User with username: " + u.getName() + " have been created.");
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
                logger.info("loginService: User with username: " + i.getName() + " have been logged in.");
                o.setItems(UserWorldDBImpl.getInstance().getItems(o.getId()));
                return Response.status(200).entity(o).build() ;
            }
            else {
                logger.info("loginService: User with username: " + i.getName() + " doesn't exist in db");
                return Response.status(404).entity("404 Not Found").type("text/plain").build() ;
            }

        }
        catch (DAOUserException e){
            logger.warn("loginService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @GET
    @Path("/{id}/items/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserItems(@PathParam("id") int userId) throws ApiException {
        List<Item> items;
        try {
            items = UserWorldDBImpl.getInstance().getItems(userId);
            logger.info("loginService: items from user retreived");
            return Response.status(200).entity(items).build();
            }

        catch (DAOUserException e){
            logger.warn("loginService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @POST
    @Path("/{id}/items/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserItemService(UserItem uI) throws ApiException {
        try {
            Boolean successful = UserItemWorldDBImpl.getInstance().setUserItem(uI);
            logger.info("addItemService: Item with id: " + uI.getItemId()+ " and " + uI.getUserId()+ " have been added to DB.");
            return Response.status(200).entity(successful).build() ;
        }
        catch (UserItemWorldDbException e) {
            logger.warn("addItemService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }



}
