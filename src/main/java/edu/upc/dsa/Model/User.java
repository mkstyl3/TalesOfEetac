package edu.upc.dsa.Model;




import java.util.HashMap;


public class User {

    //Private atributes

    private int id;
    private String username;
    private String password;
    private int lastMap;
    private HashMap<String,Integer> items;
    private Location location;

    //Constructors

    public User () {
        this.items = new HashMap<String, Integer>();
    }

    public User(int id, String username, String password, Location location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.items = new HashMap<String, Integer>();
        this.location = location;

    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.items = new HashMap<String, Integer>();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getlastMap () {return lastMap;}

    public void setlastMap (int lastMap) {this.lastMap = lastMap;}

    public void setAllItems(HashMap<String,Integer> items) {
        this.items = items;
    }

    public HashMap<String,Integer> getAllItems() {
        return items;
    }

    public void setItem (String item,Integer cantidad) {

        this.items.put(item,cantidad);
    }

    public Integer getItem (String item) {

        Integer cantidad = this.items.get(item);

        if (cantidad == null){
        return 0;
        }
        else {
            return cantidad;
        }


    }
}
