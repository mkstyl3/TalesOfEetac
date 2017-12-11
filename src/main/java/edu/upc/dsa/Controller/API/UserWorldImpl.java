package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Model.Main.User;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.Map;

public class UserWorldImpl implements UserWorld {

    //Variable declarations

    private static UserWorldImpl instance = null;
    final static Logger logger = Logger.getLogger(UserWorldImpl.class);
    private Map<Integer, User> map; // key=userId

    //Constructor

    public UserWorldImpl(){
        map = new HashMap<>();
    }

    //Getters and Setters

    public Map<Integer, User> getMap() {
        return map;
    }
    public void setMap(Map<Integer, User> map) {
        this.map = map;
    }
    public String getUsername (int id) {
        return get(id).getUsername();
    }

    //Singleton pattern

    public static UserWorldImpl getInstance() {
        if (instance == null) instance = new UserWorldImpl();
        return instance;
    }

    //Private functions

    private Boolean doExist(int id){
        logger.info("doExist: Checking if user id: "+id+" exists...");

        if(map.containsKey(id)) {
            logger.info("doExist: User with id: "+id+" already exists");
            return true;
        }

        else {
            logger.info("doExist: User with id: "+id+" doesn't exist");
            return false;
        }
    }

    private ArrayList<User> sortUsersById(ArrayList<User> users) {
        users.sort(Comparator.comparing(User::getId));
        return users;
    }

    //Public functions

    //General Functions

    /*public Boolean initializeUsers() {

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

        set(usr_1);
        set(usr_2);
        set(usr_3);
        set(usr_4);

        // usr_4 has no items

        return true;
    }*/

    public ArrayList<User> getAll() {
        return new ArrayList<>(map.values());
    }

    public ArrayList<User> getAllSortedById() {
        return sortUsersById(getAll());
    }


    //User Specific

    public Boolean set(User u) {
        logger.info("set: Creating user: "+u.getUsername()+" ...");

        if(doExist(u.getId())) {
            logger.warn("set: User: "+u.getUsername()+" already exists");
            return false;
        }

        else {
            map.put(u.getId(), u);
            logger.info("set: User: "+u.getUsername()+" have been created!");
            return true;
        }
    }

    public Boolean del(int id) {
        logger.info("del: Removing user id "+id+" ...");

        if(doExist(id)) {
            map.remove(id);
            logger.info("del: User id: "+id+" already removed");
            return true;
        }

        else {
            logger.fatal("del: Couldn't del user id: "+id);
            return false;
        }
    }

    public User get(int id) {
        logger.info("get: Querying user id: "+id+" info...");

        if (doExist(id)) {
            logger.info("get: Retreived user id: "+id+" information");
            return map.get(id);
        }
        else {
            logger.fatal("get: Couldn't retreive user id: "+id+" information");
            return null;
        }

    }

    /*public boolean setItem(int userId, Item i) {
        String username = getUsername(userId);
        logger.info("addItemUs/er: Adding item "+i.getName()+" to user: "+username);
        get(userId).setItem(i);

        logger.info("setItem: Item "+i.getName()+" added to user: "+username);
        return true;
    }

    public List<Item> getItems(int userId) {
        logger.info("getItems: Getting user item list...");

        User u = get(userId);
        if (u.getItems().isEmpty())
            logger.info("getItems: The user: "+u.getUsername()+" has no items");
        else
            logger.info("getItems: User's items obtained");

        return u.getItems();
    }

    public ArrayList<Item> getItemByName(int userId, String itemName) {
        User u = get(userId);
        logger.warn("getItemByName: Retreiving item: "+itemName+" from user: "+u.getUsername()+" ...");

        List<Item> itemList = u.getItems(); //Call economy
        ArrayList<Item> temp = new ArrayList<>();
        for (Item i: itemList) {
            if(i.getName().equals(itemName)) {
                temp.add(i);
            }
        }
        if (temp.isEmpty()) {
            logger.warn("getItemByName: User: "+u.getUsername()+" hasn't got any item with the name: "+itemName);
            return null;
        }
        else {
            logger.info("getItemByName: Item's: "+itemName+" information retreived");
            return temp;
        }
    }

    public boolean delItems(int userid) {

        String username = getUsername(userid);
        logger.info("delItems: Deleting all user: "+username+" items...");
        getItems(userid).clear();
        logger.info("All items deleted from the user: "+username);

        return true;
    }

    //LOCAL, EXAM PURPOSES ONLY
    public boolean transferItem(User origin, User destination, Item i) {
        logger.info("transferItem: Transfering item: "+i.getName()+" from user "+origin.getUsername()+" to "+destination.getUsername());

        destination.getItems().add(origin.getItem(i.getId()));
        logger.info("transferItem: Removing item: "+i.getName()+"from original user: "+origin.getUsername());
        try {
            origin.getItems().remove(origin.getItems().size()-1);
            logger.info("transferItem: Item: "+i.getName()+" removed from original user: "+origin.getUsername());
            return true;
        } catch (Exception ex) {
            logger.fatal("transferItem: IndexOutOfBoundsException Exception: Couldn't remove item: "+i.getName()+" from original user: "+origin.getUsername());
            return false;
        }
    }*/

}
