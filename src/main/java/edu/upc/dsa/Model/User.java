package edu.upc.dsa.Model;



import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private int atk;
    private int def;
    private int vit;

    private ArrayList<Item> items;
    private Location location;

    public User(int id, String username, String password, int atk, int def, int vit, Location location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.atk = atk;
        this.def = def;
        this.vit = vit;
        this.items = new ArrayList<>();
        this.location = location;

    }
    public User (){}

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

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }


    /*public void setItems(ArrayList<Item> items) { PRODUCES ERROR WITH MediaType.APPLICATION_JSON
        this.items = items;
    }*/

    public ArrayList<Item> getItems() {
        return items;
    }

    //Custom

    public void setItem (Item item) {
        this.items.add(item);
    }

    public Item getItem (int id) {
        for (Item i: this.items) {
            if (i.getId() == id) return i;
        }
        return null;
    }



}
