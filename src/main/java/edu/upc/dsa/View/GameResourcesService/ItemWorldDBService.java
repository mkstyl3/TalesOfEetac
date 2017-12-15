package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.ItemWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.ApiException;
import edu.upc.dsa.ExceptionHandler.ItemWorldDbException;
import edu.upc.dsa.Model.Main.Item;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/db/item")
@Singleton //We need it to say jersey to use an unique instance
public class ItemWorldDBService {
    final static Logger logger = Logger.getLogger(UserWorldDBService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemService(Item i) throws ApiException {
        try {
            Boolean successful = ItemWorldDBImpl.getInstance().setItem(i);
            logger.info("addItemService: Item: " + i.getName() + " have been added to DB.");
            return Response.status(200).entity(successful).build() ;
        }
        catch (ItemWorldDbException e) {
            logger.warn("registerService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

}
