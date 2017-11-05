package edu.upc.dsa.Model.Cell;

import edu.upc.dsa.Model.Location;

public class Wall extends Cell {

    final protected String symbol = "#";

    public Wall () {}

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
