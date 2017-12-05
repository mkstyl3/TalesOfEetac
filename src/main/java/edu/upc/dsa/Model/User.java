package edu.upc.dsa.Model;




import java.util.HashMap;


public class User {

    //Private atributes

    private int id;
    private String username;
    private String password;
    private HashMap<String,Integer> items;
    private Location location;

    //Constructors

    public User () {
        this.items = new HashMap<>();
    }

    public User(int id, String username, String password, Location location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.items = new HashMap<>();
        this.location = location;

    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.items = new HashMap<>();
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

    public void setItems(HashMap<String,Integer> items) {
        this.items = items;
    }

    public HashMap<String,Integer> getItems() {
        return items;
    }

    //Getters and Setters from "items" (It's a list)

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
