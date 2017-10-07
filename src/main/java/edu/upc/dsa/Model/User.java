package edu.upc.dsa.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private int level;
    private int atk;
    private int def;
    private int vit;
    private ArrayList<Item> userItemList;

    public User(int id, String name, String password, int level, int atk, int def, int vit) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.atk = atk;
        this.def = def;
        this.vit = vit;
        this.userItemList = new ArrayList<Item>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public ArrayList<Item> getUserItemList() {
        return userItemList;
    }

    public void setUserItemList(ArrayList<Item> userItemList) {
        this.userItemList = userItemList;
    }

    //Custom

    public void setItem (Item item) {
        this.userItemList.add(item);
    }

    public Item getItem (int id) {
        for (Item i: this.userItemList) {
            if (i.getId() == id) return i;
        }
        return null;
    }



}
