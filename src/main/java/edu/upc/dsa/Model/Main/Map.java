package edu.upc.dsa.Model.Main;

import edu.upc.dsa.Model.Cell.Cell;
import edu.upc.dsa.Model.Main.Location;

public class Map {

    private int id;
    private Cell[] cellArray;

    public Map () {}

    public Map (int id, Cell[] cellmap) {
        this.id = id;
        cellArray = new Cell[100];
        this.cellArray = cellmap;
    }

    public Cell getCell (Location cellLoc) {
        return cellArray[cellLoc.getX()*10+cellLoc.getY()];
    }

    public Cell getCellByCoords (int x, int y) {
        return cellArray[x*10+y];
    }


    public void setCell (Cell cell) {
        this.cellArray[cell.getOnMapLoc().getX()*10+cell.getOnMapLoc().getY()] = cell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
