package edu.upc.dsa.Model.Cell;

import edu.upc.dsa.Model.Location;

public class Door extends Cell {


    private int nextMap;
    final private String symbol = "D";

    public Door () { } //"yes, it is unnecessary to include super() in the child constructor", because super() is a call to the "accessible no-args constructor"

    public int getNextMap() {
        return nextMap;
    }

    public void setNextMap(int nextmap) {
        this.nextMap = nextmap;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
