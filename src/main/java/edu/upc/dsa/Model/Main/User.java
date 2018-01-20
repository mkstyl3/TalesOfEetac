package edu.upc.dsa.Model.Main;




import java.util.ArrayList;
import java.util.List;


public class User {

    //Private atributes

    private int id;
    private String name;
    private String password;
    private String email;
    private List<Item> items;
    private Location location;

    //Constructors

    public User () {
        this.items = new ArrayList<>();
    }

    public User(int id, String name, String password, String email, Location location) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.items = new ArrayList<>();
        this.location = location;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.items = new ArrayList<>();

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //Getters and Sertters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setAllItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getAllItems() {
        return items;
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
