package edu.upc.dsa.Model;

public class Item {

    //Variable declarations

    private int id;
    private String name;
    private int type;
    private String description;
    private double value;
    private int cost;

    //Constructors

    public Item(int id, String name, int type, String description, double value, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.value = value;
        this.cost = cost;
    }

    public Item (){}

    //Getters and Setters

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
