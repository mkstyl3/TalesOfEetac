package edu.upc.dsa.Model;

public class Location {

    //Private variables
    private int x;
    private int y;

    //Constructors
    public Location (){ }
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Getters and Setters
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setCoords (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
