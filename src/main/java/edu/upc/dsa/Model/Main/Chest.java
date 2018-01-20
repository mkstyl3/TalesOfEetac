package edu.upc.dsa.Model.Main;

import java.util.ArrayList;
import java.util.List;

public class Chest {

    int id;
    String description;

    List<Item> items;

    public Chest() {
        items = new ArrayList<>();
    }

    public Chest (int id, String description) {
        this.id = id;
        this.description = description;
        items = new ArrayList<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
