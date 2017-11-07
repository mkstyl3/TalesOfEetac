package edu.upc.dsa.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.*;
import edu.upc.dsa.Model.Cell.*;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.Map;
import edu.upc.dsa.Model.User;
import org.apache.log4j.Logger;

import java.io.*;

public class WorldGameScreen {

    //Variable declarations
    final static Logger logger = Logger.getLogger(WorldUser.class);
    private static WorldGameScreen instance = null;

    private int currentMapId = 1;
    private Map[] mapsArray;
    private Cell lastUserCell;



    //Getters and setters
    public Map[] getMapsArray() {
        return mapsArray;
    }
    public void setMapsArray(Map[] mapsArray) {
        this.mapsArray = mapsArray;
    }

    public Cell getLastUserCell() {
        return lastUserCell;
    }
    public void setLastUserCell(Cell lastUserCell) {
        this.lastUserCell = lastUserCell;
    }
    public int getCurrentMapId() {
        return currentMapId;
    }
    public void setCurrentMapId(int currentMapId) {
        this.currentMapId = currentMapId;
    }

    //Singleton pattern
    public static WorldGameScreen getInstance() {
        if (instance == null) instance = new WorldGameScreen();
        return instance;
    }

    //Public functions

    public void objectInitializations () {
        mapsArray = new Map[5];
        lastUserCell = new UserCell();
    }

    public Map createMap (int mapId) {
        logger.info("loadMap: Loading map...");
        try {
            StringBuilder s = new StringBuilder();
            s.append("map").append(mapId).append(".txt");
            ObjectMapper mapper = new ObjectMapper();
            Cell cell[] = mapper.readValue(new File("src/main/resources/"+s), Cell[].class);
            Map map = new Map(mapId, cell);
            logger.info("loadMap: map loaded.");

            return map;
        }
        catch (IOException ex)
        {
            logger.fatal("File not found");

            return null;
        }
    }

    public void loadMap (Map map) {
        this.mapsArray[map.getMapId() - 1] = map;
    }

    public void initialUserLocation(User u) {

        Cell cell = new UserCell(u);
        lastUserCell = mapsArray[currentMapId - 1].getCell(u.getLocation());
        mapsArray[currentMapId - 1].setCell(cell);
    }

    public void moveUserTo (User u, String direction) {
        Location nextCellLoc = new Location();
        int analysis = 0;
        switch (direction) {
            case "a":
                nextCellLoc.setCoords(u.getLocation().getX(), u.getLocation().getY() - 1);
                break;
            case "d":
                nextCellLoc.setCoords(u.getLocation().getX(), u.getLocation().getY() + 1);
                break;
            case "w":
                nextCellLoc.setCoords(u.getLocation().getX() - 1, u.getLocation().getY());
                break;
            case "s":
                nextCellLoc.setCoords(u.getLocation().getX() + 1, u.getLocation().getY());
                break;
        }
        switch (mapsArray[currentMapId - 1].getCell(nextCellLoc).getSymbol()) {
            case "#":
                break;
            case "T":
                break;
            case "C":
                break;
            case "N":
                break;
            case "D":
                locateUserAtNextDoorLocation(nextCellLoc, u); //Get next door by door coords
                break;
            default:
                Cell nextUserCell = mapsArray[currentMapId -1].getCell(nextCellLoc); //Pick the next cell
                u.setLocation(nextCellLoc); //Update user new Location
                mapsArray[currentMapId - 1].setCell(new UserCell(u)); //Put the user in the next cell
                mapsArray[currentMapId -1].setCell(lastUserCell); //Replace with last visited
                lastUserCell = nextUserCell; //Update last visited
                break;
        }
    }

    private void locateUserAtNextDoorLocation(Location currentDoorLoc, User u) {

        Door doorCell = (Door)mapsArray[currentMapId - 1].getCell(currentDoorLoc);
        int nextMapId = doorCell.getNextMap();

        switch (nextMapId) {
            case 2:
                currentMapId =2;
                if (currentDoorLoc.getX() == 9 && currentDoorLoc.getY() == 4) {
                    loadMap(createMap(nextMapId));
                    Cell nextUserCell = mapsArray[currentMapId -1].getCellByCoords(1,4);
                    u.setLocation(nextUserCell.getOnMapLoc());
                    mapsArray[currentMapId - 1].setCell(new UserCell(u));
                    mapsArray[currentMapId -2].setCell(lastUserCell);
                    lastUserCell = nextUserCell;
                }
                break;
            case 1:
                currentMapId = 1;
                if (currentDoorLoc.getX() == 0 && currentDoorLoc.getY() == 4) {
                    Cell nextUserCell = mapsArray[currentMapId -1].getCellByCoords(8,4);
                    u.setLocation(nextUserCell.getOnMapLoc());
                    mapsArray[currentMapId - 1].setCell(new UserCell(u));
                    mapsArray[currentMapId].setCell(lastUserCell);
                    lastUserCell = nextUserCell;
                }
                break;
            default: // You here can write more maps
                break;
        }
    }


}
