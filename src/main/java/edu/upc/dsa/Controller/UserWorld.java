package edu.upc.dsa.Controller;

import org.apache.log4j.Logger;
import edu.upc.dsa.Model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/user")
public class UserWorld implements IUserWorld {

    //Variable declarations
    private static UserWorld instance = null;
    final static Logger logger = Logger.getLogger(UserWorld.class);
    private HashMap<Integer, User> usersMap;

    //Constructor
    public UserWorld(){
        usersMap = new HashMap<>();
    }

    //Getters and setters
    public HashMap<Integer, User> getUsersMap() {
        return usersMap;
    }
    public void setUsersMap(HashMap<Integer, User> usersMap) {
        this.usersMap = usersMap;
    }

    //Singleton pattern
    public static UserWorld getInstance() {
        if (instance == null) instance = new UserWorld();
        return instance;
    }

    //Private functions
    private Boolean userExist(int id){
        logger.info("userExist: Checking if user id: "+id+" exists...");

        if(usersMap.containsKey(id)) {
            logger.info("userExist: User with id: "+id+" already exists");
            return true;
        }

        else {
            logger.info("userExist: User with id: "+id+" doesn't exist");
            return false;
        }
    }

    //Public functions

    public Boolean initializeUsers() {


        User usr_1 = new User(1, "Marc", "1234", 34, 30, 30, new Location(5,4));
        User usr_2 = new User(2, "Gerard", "1234", 34, 30, 30, new Location(5,4));
        User usr_3 = new User(3, "Ivan", "1234", 34, 30, 30, new Location(5,4));
        User usr_4 = new User(4, "Mario", "1234", 34, 30, 30, new Location(5,4));

        Item item_1 = new Item(1,"potion", 3, "minor heal", 0.2, 20);
        Item item_2 = new Item(2,"paralize", 3, "minor heal", 0.2, 20);
        Item item_3 = new Item(3,"mana", 3, "minor heal", 0.2, 20);
        Item item_4 = new Item(4,"speed", 3, "minor heal", 0.2, 20);

        usr_1.setItem(item_1);
        usr_1.setItem(item_2);
        usr_1.setItem(item_3);
        usr_1.setItem(item_4);

        usr_2.setItem(item_1);
        usr_2.setItem(item_2);
        usr_2.setItem(item_3);

        usr_3.setItem(item_1);
        usr_3.setItem(item_2);

        // usr_4 has no items

        return true;
    }

    public void initializeUserObjects () {

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean createUser(User usr) {
        logger.info("createUser: Creating user: "+usr.getUsername()+" ...");

        if(userExist(usr.getId())) {
            logger.warn("createUser: User: "+usr.getUsername()+" already exists");
            return false;
        }

        else {
            usersMap.put(usr.getId(), usr);
            logger.info("createUser: User: "+usr.getUsername()+" have been created!");
            return true;
        }
    }

    @POST
    @Path("/test")
    @Consumes (MediaType.APPLICATION_JSON)
    public String testFuntion(User u) {
        return "hello test";
    }

    public Boolean deleteUser(int id) {
        logger.info("deleteUser: Removing user id "+id+" ...");

        if(userExist(id)) {
            usersMap.remove(id);
            logger.info("deleteUser: User id: "+id+" already removed");
            return true;
        }

        else {
            logger.fatal("deleteUser: Couldn't delete user id: "+id);
            return false;
        }
    }

    public User queryUser(int id) {
        logger.info("queryUser: Querying user id: "+id+" info...");

        if (userExist(id)) {
            logger.info("queryUser: Retreived user id: "+id+" information");
            return usersMap.get(id);
        }
        else {
            logger.fatal("queryUser: Couldn't retreive user id: "+id+" information");
            return null;
        }

    }

    public void addItemUser(User usr, Item i) {
        logger.info("addItemUser: Adding item "+i.getName()+" to user: "+usr.getUsername());

        usr.getUserItemList().add(i);
        logger.info("addItemUser: Item "+i.getName()+" added to user: "+usr.getUsername());
    }

    public ArrayList<Item> userItemListQuery(User usr) {
        logger.info("userItemListQuery: Getting user item list...");

        if (usr.getUserItemList().isEmpty())
            logger.info("userItemListQuery: The user: "+usr.getUsername()+" has no items");
        else
            logger.info("userItemListQuery: User's items obtained");

        return usr.getUserItemList();
    }

    public ArrayList<Item> queryUserItemByName(User usr, String itemName) {
        logger.warn("queryUserItemByName: Retreiving item: "+itemName+" from user: "+usr.getUsername()+" ...");

        ArrayList<Item> list = usr.getUserItemList();
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item i: list) {
            if(i.getName().equals(itemName)) {
                temp.add(i);
            }
        }
        if (temp.isEmpty()) {
            logger.warn("queryUserItemByName: User: "+usr.getUsername()+" hasn't got any item with the name: "+itemName);
            return temp;
        }
        else {
            logger.info("queryUserItemByName: Item's: "+itemName+" information retreived");
            return temp;
        }
    }

    public void deleteUserItems(User usr) {
        logger.info("deleteUserItems: Deleting all user: "+usr.getUsername()+" items...");

        usr.getUserItemList().clear();
        if (usr.getUserItemList().isEmpty()) logger.info("All items deleted from the user: "+usr.getUsername());
        else logger.fatal("deleteUserItems: Couldn't delete all user: "+usr.getUsername()+" items");
    }

    public void userToUserItemTransfer(User origin, User destination, Item i) {
        logger.info("userToUserItemTransfer: Transfering item: "+i.getName()+" from user "+origin.getUsername()+" to "+destination.getUsername());

        destination.getUserItemList().add(origin.getItem(i.getId()));
        logger.info("userToUserItemTransfer: Removing item: "+i.getName()+"from original user: "+origin.getUsername());
        try {
            origin.getUserItemList().remove(origin.getUserItemList().size()-1);
            logger.info("userToUserItemTransfer: Item: "+i.getName()+" removed from original user: "+origin.getUsername());
        } catch (Exception ex) {
            logger.fatal("userToUserItemTransfer: IndexOutOfBoundsException Exception: Couldn't remove item: "+i.getName()+" from original user: "+origin.getUsername());
        }
    }

}
