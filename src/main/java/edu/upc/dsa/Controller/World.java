package edu.upc.dsa.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import edu.upc.dsa.Model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class World implements WorldInterface {

    //Variable declarations

    final static Logger logger = Logger.getLogger(World.class);
    private HashMap<Integer, User> usersMap; //For test purposes
    private LinkedHashMap<Integer, String> hashOfMaps; //Mantains the insertion order for painting the map later

    public HashMap<Integer, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(HashMap<Integer, User> usersMap) {
        this.usersMap = usersMap;
    }

    public LinkedHashMap<Integer, String> getHashOfMaps() {
        return hashOfMaps;
    }

    public void setHashOfMaps(LinkedHashMap<Integer, String> hashOfMaps) {
        this.hashOfMaps = hashOfMaps;
    }

    //Singleton pattern

    private static World instance = null;

    public static World getInstance() {
        if (instance == null) instance = new World();
        return instance;
    }
    public World(){
        usersMap = new HashMap<Integer, User>();
    }

    //Public functions

    public boolean loadMap (String mapName) {
        logger.info("loadMap: Loading map...");

        hashOfMaps = new LinkedHashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/"+mapName)));
            Type type = new TypeToken<LinkedHashMap<String, String>>(){}.getType();
            hashOfMaps = new Gson().fromJson(reader, type);


            logger.info("loadMap: map loaded.");
            return true;
        }
        catch (IOException ex)
        {
            logger.fatal("File not found");
            return false;
        }

    }

    public Boolean userExist(int id){
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

    public Boolean createUser(User usr) {
        logger.info("createUser: Creating user: "+usr.getName()+" ...");

        if(userExist(usr.getId())) {
            logger.warn("createUser: User: "+usr.getName()+" already exists");
            return false;
        }

        else {
            usersMap.put(usr.getId(), usr);
            logger.info("createUser: User: "+usr.getName()+" have been created!");
            return true;
        }
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
        logger.info("addItemUser: Adding item "+i.getName()+" to user: "+usr.getName());

        usr.getUserItemList().add(i);
        logger.info("addItemUser: Item "+i.getName()+" added to user: "+usr.getName());
    }

    public ArrayList<Item> userItemListQuery(User usr) {
        logger.info("userItemListQuery: Getting user item list...");

        if (usr.getUserItemList().isEmpty())
            logger.info("userItemListQuery: The user: "+usr.getName()+" has no items");
        else
            logger.info("userItemListQuery: User's items obtained");

        return usr.getUserItemList();
    }

    public ArrayList<Item> queryUserItemByName(User usr, String itemName) {
        logger.warn("queryUserItemByName: Retreiving item: "+itemName+" from user: "+usr.getName()+" ...");

        ArrayList<Item> list = usr.getUserItemList();
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item i: list) {
            if(i.getName().equals(itemName)) {
                temp.add(i);
            }
        }
        if (temp.isEmpty()) {
            logger.warn("queryUserItemByName: User: "+usr.getName()+" hasn't got any item with the name: "+itemName);
            return temp;
        }
        else {
            logger.info("queryUserItemByName: Item's: "+itemName+" information retreived");
            return temp;
        }
    }

    public void deleteUserItems(User usr) {
        logger.info("deleteUserItems: Deleting all user: "+usr.getName()+" items...");

        usr.getUserItemList().clear();
        if (usr.getUserItemList().isEmpty()) logger.info("All items deleted from the user: "+usr.getName());
        else logger.fatal("deleteUserItems: Couldn't delete all user: "+usr.getName()+" items");
    }

    public void userToUserItemTransfer(User origin, User destination, Item i) {
        logger.info("userToUserItemTransfer: Transfering item: "+i.getName()+" from user "+origin.getName()+" to "+destination.getName());

        destination.getUserItemList().add(origin.getItem(i.getId()));
        logger.info("userToUserItemTransfer: Removing item: "+i.getName()+"from original user: "+origin.getName());
        try {
            origin.getUserItemList().remove(origin.getUserItemList().size()-1);
            logger.info("userToUserItemTransfer: Item: "+i.getName()+" removed from original user: "+origin.getName());
        } catch (Exception ex) {
            logger.fatal("userToUserItemTransfer: IndexOutOfBoundsException Exception: Couldn't remove item: "+i.getName()+" from original user: "+origin.getName());
        }
    }
}
