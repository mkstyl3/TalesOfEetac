package edu.upc.dsa.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.Model.Cell.*;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.Map;
import edu.upc.dsa.Model.User;
import org.apache.log4j.Logger;

import java.io.*;

public class GameScreenWorld implements IGameScreenWorld {

    //Variable declarations
    final static Logger logger = Logger.getLogger(UserWorld.class);
    private static GameScreenWorld instance = null;

    private int currentMapId = 1;
    private Map[] mapsArray;
    private Cell lastUserCell;



    //Getters and setters
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

    public Map getMap (int id) {
        return this.mapsArray[id - 1];
    }

    public void setMap (Map map) {
        this.mapsArray[map.getId() -1] = map;
    }

    public void setCell(int mapId, Cell cell) {
        this.getMap(mapId).setCell(cell);
    }

    public Cell getCell(int mapId, Location l) {
        return getMap(mapId).getCell(l);
    }
    public Cell getCellByCoords(int mapId, int x, int y) {
        return getMap(mapId).getCellByCoords(x,y);
    }

    //Singleton pattern
    public static GameScreenWorld getInstance() {
        if (instance == null) instance = new GameScreenWorld();
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

    public void initialUserLocation(User u) {

        Cell cell = new UserCell(u);
        lastUserCell = getCell(currentMapId, u.getLocation());
        setCell(currentMapId,cell);
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
        switch (getCell(currentMapId, nextCellLoc).getSYMBOL()) {
            case "#":
                break;
            case "T":
                break;
            case "C":
                break;
            case "N":
                NPC npcCell = (NPC)getCell(currentMapId, nextCellLoc);
                npcCell.setDialogue("HOLA K TAL");
                System.out.println(npcCell.getDialogue());
                break;
            case "D":
                locateUserAtNextDoorLocation(nextCellLoc, u); //Get next door by door coords
                break;
            default:
                Cell nextUserCell = getCell(currentMapId, nextCellLoc); //Pick the next cell
                u.setLocation(nextCellLoc); //Update user new Location
                setCell(currentMapId, new UserCell(u)); //Put the user in the next cell
                setCell(currentMapId, lastUserCell); //Replace with last visited
                lastUserCell = nextUserCell; //Update last visited
                break;
        }
    }

    public void locateUserAtNextDoorLocation(Location currentDoorLoc, User u) {

        Door doorCell = (Door)getCell(currentMapId, currentDoorLoc);
        int nextMapId = doorCell.getNextMap();

        switch (nextMapId) {
            case 2:
                currentMapId =2;
                if (currentDoorLoc.getX() == 9 && currentDoorLoc.getY() == 4) {
                    setMap(createMap(nextMapId));
                    Cell nextUserCell = getCellByCoords(currentMapId,1,4);
                    u.setLocation(nextUserCell.getOnMapLoc());
                    setCell(currentMapId, new UserCell(u));
                    setCell(currentMapId - 1, lastUserCell);
                    lastUserCell = nextUserCell;
                }
                break;
            case 1:
                currentMapId = 1;
                if (currentDoorLoc.getX() == 0 && currentDoorLoc.getY() == 4) {
                    Cell nextUserCell = getCellByCoords(currentMapId,8,4);
                    u.setLocation(nextUserCell.getOnMapLoc());
                    setCell(currentMapId, new UserCell(u));
                    setCell(currentMapId + 1, lastUserCell);
                    lastUserCell = nextUserCell;
                }
                break;
            default: // You here can write more maps
                break;
        }
    }

}
