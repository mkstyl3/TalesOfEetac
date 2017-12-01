package edu.upc.dsa.Model.Cell;

import edu.upc.dsa.Model.Location;

public class Field extends Cell {

    final protected String symbol = " ";

    public Field () {}

    @Override
    public String getSYMBOL() {
        return this.symbol;
    }
}
