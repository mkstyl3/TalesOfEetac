package edu.upc.dsa.Model.Cell;

public class Wall extends Cell {

    final protected String symbol = "#";

    public Wall () {}

    @Override
    public String getSYMBOL() {
        return this.symbol;
    }
}
