package edu.upc.dsa.Model.Main;




import java.util.ArrayList;
import java.util.List;


public class User {

    //Private atributes

    private int id;
    private String name;
    private String password;
    private int lastMap;
    private List<Item> items;
    private Location location;
    private boolean admin;

    //Constructors

    public User () {
        this.items = new ArrayList<>();
    }

    public User(int id, String name, String password, Location location) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.items = new ArrayList<>();
        this.location = location;
    }

    public User(int id, String name, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.items = new ArrayList<>();

    }

    //Getters and Sertters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLastMap() {return lastMap;}

    public void setLastMap(int lastMap) {this.lastMap = lastMap;}

    public void setAllItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getAllItems() {
        return items;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setItem (Item i) {
        this.items.add(i);
    }
    public Item getItem(int position) {
        return this.items.get(position);
    }
}
