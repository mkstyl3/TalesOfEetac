package edu.upc.dsa.View.GameResourcesService;

import edu.upc.dsa.Controller.API.UserWorldImpl;
import edu.upc.dsa.Model.Main.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/user")
@Singleton //We need it to say jersey to use an unique instance
public class UserWorldService {

    //Testing purposes "/user"
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean createUserService(User u) {
        return UserWorldImpl.getInstance().set(u);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsersService() {
        return UserWorldImpl.getInstance().getAll();
    }

    @GET
    @Path("/allSortedById")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsersSortedByIdService() {
        return UserWorldImpl.getInstance().getAllSortedById();
    }

    @GET
    @Path("/del/{id}")
    public Boolean deleteUserService(@PathParam("id") int id) {
        return UserWorldImpl.getInstance().del(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User queryUserService(@PathParam("id") int id) {
        return UserWorldImpl.getInstance().get(id);
    }

    /*@POST
    @Path("/{id}/items/setItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addItemUserService(@PathParam("id") int userId, Item i){
        return UserWorld.getInstance().setItem(userId,i);
    }

    @GET
    @Path("/{id}/items/allItems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> userItemListQueryService(@PathParam("id") int userId){
        return UserWorld.getInstance().getItems(userId);
    }

    @GET
    @Path("/{id}/items/{itemName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> queryUserItemByNameService(@PathParam("id")int userId, @PathParam("itemName")String itemName){
        return UserWorld.getInstance().getItemByName(userId,itemName);
    }

    @GET
    @Path("/{id}/items/deleteAll")
    public boolean deleteUserItems(@PathParam("id")int userId) {
        return UserWorld.getInstance().delItems(userId);
    }

    @POST
    @Path("/{id}/items/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean userToUserItemTransferService(Transfer transfer) {
        return UserWorld.getInstance().transferItem((User)transfer.getOrigin(), (User)transfer.getDestination(), (Item)transfer.getItem());
    }*/




}
