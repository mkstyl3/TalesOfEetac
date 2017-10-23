package edu.upc.dsa.Model;

//Functions where involve Cells and what the can contain: Users, Objects or Enemies

public class Cell {

    public Cell(int type, int currentMap, int nextMap, String symbol) {
        this.type = type;
        this.currentMap = currentMap;
        this.nextMap = nextMap;
        this.symbol = symbol;
    }

    private int type;
    private int currentMap;
    private int nextMap;
    private String symbol;


    public int getType() {
        return this.type;
    }


    public int getCurrentMap() {
        return this.currentMap;
    }


    public int getNextMap() {
        return this.nextMap;
    }


    public String getSymbol() {
        return this.symbol;
    }
}
