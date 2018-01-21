package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.ChestItemWorldDBImpl;
import edu.upc.dsa.Controller.API.UserItemWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.ApiException;
import edu.upc.dsa.ExceptionHandler.ChestItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.Model.Relation.ChestItem;
import edu.upc.dsa.Model.Relation.UserItem;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/db/userItem")
@Singleton //We need it to say jersey to use an unique instance
public class UserItemWorldDBService {
    final static Logger logger = Logger.getLogger(UserWorldDBService.class);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserItemService(UserItem userItem) throws ApiException {
        try {
            Boolean successful = UserItemWorldDBImpl.getInstance().setUserItem(userItem);
            logger.info("addUserItemService: " + userItem.getId() + " have been added to DB.");
            return Response.status(200).entity(successful).build() ;
        }
        catch (UserItemWorldDbException e) {
            logger.warn("addUserItemService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

}
