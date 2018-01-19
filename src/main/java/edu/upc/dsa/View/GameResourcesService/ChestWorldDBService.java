package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.ChestItemWorldDBImpl;
import edu.upc.dsa.Controller.API.ChestWorldDBImpl;

import edu.upc.dsa.ExceptionHandler.*;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.ChestItem;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/db/chest")
@Singleton //We need it to say jersey to use an unique instance
public class ChestWorldDBService {
    final static Logger logger = Logger.getLogger(ChestWorldDBService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addChestService(Chest c) throws ApiException {
        try {
            Boolean successful = ChestWorldDBImpl.getInstance().setChest(c);
            logger.info("addItemService: Item: " + c.getId() + " have been added to DB.");
            return Response.status(200).entity(successful).build() ;
        }
        catch (ChestWorldDbException e) {
            logger.warn("registerService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @POST
    @Path("/{id}/items/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addChestItemService(ChestItem cI) throws ApiException {
        try {
            Boolean successful = ChestItemWorldDBImpl.getInstance().setChestItem(cI);
            logger.info("addItemService: Item with id: " + cI.getItemId()+ " and " + cI.getChestId()+ " have been added to DB.");
            return Response.status(200).entity(successful).build() ;
        }
        catch (ChestItemWorldDbException e) {
            logger.warn("addItemService: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @GET
    @Path("/{id}/items/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChestItems(@PathParam("id") int chestId) throws ApiException {
        List<Item> items;
        try {
            items = ChestWorldDBImpl.getInstance().getItems(chestId);
            logger.info("getChestItems: items from chest retreived");
            return Response.status(200).entity(items).build();
        }

        catch (DAOChestException e){
            logger.warn("getChestItems: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }

    @GET
    @Path("/{id}/items/deleteAll")
    public Response deleteChestItems(@PathParam("id") int chestId) throws DAOException {
        try {
            boolean bool = ChestItemWorldDBImpl.getInstance().deleteChestItems(chestId);
            logger.info("deleteChestItems: items from chest retreived");
            return Response.status(200).entity(bool).build();
        }

        catch (DAOChestException e){
            logger.warn("deleteChestItems: There is a server error. See Exception for more details.");
            throw new ApiException(e);
        }
    }



}
