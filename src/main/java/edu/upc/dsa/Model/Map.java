package edu.upc.dsa.Model;

import edu.upc.dsa.Model.Cell.Cell;

import java.util.ArrayList;

public class Map {

    private int mapId;
    private Cell[] cellMap;

    public Map () {}

    public Map (int mapId, Cell[] cellmap) {
        this.mapId = mapId;
        cellMap = new Cell[100];
        this.cellMap = cellmap;
    }

    public Cell getCell (Location cellLoc) {
        return cellMap[cellLoc.getX()*10+cellLoc.getY()];
    }

    public Cell getCellByCoords (int x, int y) {
        return cellMap[x*10+y];
    }


    public void setCell (Cell cell) {
        this.cellMap[cell.getOnMapLoc().getX()*10+cell.getOnMapLoc().getY()] = cell;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }


}
