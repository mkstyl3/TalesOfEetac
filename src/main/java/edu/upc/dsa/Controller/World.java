package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World implements WorldInterface {

    final static Logger logger = Logger.getLogger(World.class);
    Map<Integer, User> userMap = new HashMap<Integer, User>();


    private static World instance = null;

    public static World getInstance() {
        if (instance == null) instance = new World();
        return instance;
    }

    private Boolean userExist(int id){
        logger.info("Checking if user exists");
        if(userMap.containsKey(id)) {
            logger.info("OK, user already exists");
            return true;
        }

        else {
            logger.info("OK, user doesn't exist");
            return false;
        }
    }

    public Boolean createUser(User usr) {
        if(userExist(usr.getId())) {
            logger.warn("Oh no! user with that id already exists");
            return false;
        }

        else {
            userMap.put(usr.getId(), usr);
            logger.info("OK, user created!");
            return true;
        }
    }

    public Boolean deleteUser(int id) {
        if(userExist(id)) {
            userMap.remove(id);
            logger.info("OK, user already removed");
            return true;
        }

        else {
            logger.info("Oh no! Couldn't do that operation");
            return false;
        }
    }

    public User userQuery(int id) {
        if (userExist(id)) {
            logger.info("OK, retreived user information");
            return userMap.get(id);
        }
        else {
            logger.warn("Oh no! Couldn't do that operation");
            return null;
        }

    }

    public void addItemUser(User usr, Item i) {

        usr.getUserItemList().add(i);
        logger.info("OK, item added to user");
    }

    public List<Item> userItemListQuery(User usr) {
        logger.info("OK, retreived user item list, maybe it's empty!");
        return usr.getUserItemList();
    }

    public ArrayList<Item> queryUserItem(User usr, String itemName) {
        ArrayList<Item> list = usr.getUserItemList();
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item i: list) {
            if(i.getName().equals(itemName)) {
                temp.add(i);
            }
        }
        if (temp.isEmpty()) {
            logger.warn("OK, the item doen't exist");
            return null;
        }
        else {
            logger.info("OK, the item doen't exist");
            return temp;
        }
    }

    public void deleteUserItems(User usr) {
        usr.getUserItemList().clear();
        if (usr.getUserItemList().isEmpty()) logger.info("OK, all items deleted from the user");
        else logger.warn("Oh no! couldn't do that operation");
    }

    public void userToUserItemTransfer(User origin, User destination, Item i) throws IndexOutOfBoundsException {
        logger.info("Transfering item from user"+origin.getName()+"to"+destination.getName());
        destination.getUserItemList().add(origin.getUserItemList().get(i.getId()));
        logger.info("DONE, removing item from original user");
        try {
            origin.getUserItemList().remove(i.getId());
            logger.info("OK, item removed fro original");
        } catch (Exception ex) {
            logger.info("Oh no, couldnt remove the item from origin");
        }
    }
}
