package edu.upc.dsa.Controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.upc.dsa.Model.Cell;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.User;
import org.apache.log4j.Logger;
import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class WorldGameScreen {

    //Variable declarations
    final static Logger logger = Logger.getLogger(WorldUser.class);
    private static WorldGameScreen instance = null;
    private TreeMap<String, Cell> treeOfCells;
    private TreeMap<Integer, TreeMap<String, Cell>> treeOfMaps;
    Cell lastCellVisited;
    int lastMapVisited;


    //Getters and setters
    public TreeMap<String, Cell> getTreeOfCells() {
        return treeOfCells;
    }
    public void setTreeOfCells(TreeMap<String, Cell> treeOfCells) {
        this.treeOfCells = treeOfCells;
    }

    //Singleton pattern
    public static WorldGameScreen getInstance() {
        if (instance == null) instance = new WorldGameScreen();
        return instance;
    }

    //Public functions
    public void initialUserLocation() {
        lastCellVisited = treeOfCells.get("54");
        treeOfCells.put("54", new Cell(0, 1, 1, "@"));
    }
    public boolean loadMap (String mapName) {
        logger.info("loadMap: Loading map...");

        treeOfCells = new TreeMap<>();
        treeOfMaps = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/"+mapName)));
            Type type = new TypeToken<TreeMap<String, Cell>>(){}.getType();
            treeOfCells = new Gson().fromJson(reader, type);
            treeOfMaps.put(0, treeOfCells);
            logger.info("loadMap: map loaded.");
            return true;
        }
        catch (IOException ex)
        {
            logger.fatal("File not found");
            return false;
        }

    }

    public int moveUserTo (User u, String direction) {
        String currentLocation = u.getLocation().getCurrentLocation();
        int anaylysis = 0;
        switch (direction) {
            case "a":
                anaylysis = analyzeLeftCell(currentLocation);
                break;
            case "d":
                anaylysis = analyzeRightCell(currentLocation);
                break;
            case "w":
                anaylysis = analyzeUpperCell(currentLocation);
                break;
            case "s":
                anaylysis = analyzeDownCell(currentLocation);
                break;
        }

        int canOrW0t = 0;
        switch (anaylysis) {
            case 0:
                canOrW0t = 0;
                break;
            case 1:
                canOrW0t = 1;
                break;
            case 2:
                canOrW0t = 2;
                break;
            case 3:
                canOrW0t = 3;
                break;
            case 4:
                //int nextLevel = 4.getNextLevel();
                canOrW0t = 4;
                break;

            default: //can move
                String leftAnalysisResultString = String.valueOf(anaylysis);
                //I get the character where my char was.
                Cell lastCharacterVisited = this.lastCellVisited;
                //I get the character where my char has to be.
                Cell newCharacterToVisit = treeOfCells.get(leftAnalysisResultString);
                //I save the new user location.
                u.setLocation(new Location(u.getLocation().getCurrentLocation(), leftAnalysisResultString));
                //I replace old character by what it was.
                treeOfCells.put(currentLocation, lastCharacterVisited);
                //I save the location where @ was to lastCellVisited
                this.lastCellVisited = newCharacterToVisit;
                //I replace de left-character with @
                treeOfCells.put(leftAnalysisResultString, new Cell(0, 1, 1, "@"));
        }

        return canOrW0t;
    }

    public int analyzeLeftCell (String cell) {

        int cellInt = Integer.valueOf(cell);
        int leftCellInt = cellInt -1;
        String leftCell = String.valueOf(leftCellInt);
        int result = getNextCellResults(leftCell, leftCellInt);

        return result;
    }

    public int analyzeRightCell (String cell) {

        int cellInt = Integer.valueOf(cell);
        int rightCellInt = cellInt +1;
        String rightCell = String.valueOf(rightCellInt);
        int result = getNextCellResults(rightCell, rightCellInt);

        return result;
    }

    public int analyzeUpperCell (String cell) {

        int cellInt = Integer.valueOf(cell);
        int upperCellInt = cellInt -10;
        String upperCell = String.valueOf(upperCellInt);
        int result = getNextCellResults(upperCell, upperCellInt);

        return result;
    }

    public int analyzeDownCell (String cell) {

        int cellInt = Integer.valueOf(cell);
        int result=0;

        if (!cell.substring(0,1).equals("9")) {
            int downCellInt = cellInt +10;
            String downCell = String.valueOf(downCellInt);
            result = getNextCellResults(downCell, downCellInt);
        }

        return result;
    }

    private int getNextCellResults (String nextCell, int nextCellInt) {
        int result = 0;
        if (0 < nextCellInt && nextCellInt < 9) //fix for 00 - 09 numeration system
            return result;
        switch (treeOfCells.get(nextCell).getSymbol()) {
            case "#":
                result = 1;
                break;
            case "B":
                result = 2;
                break;
            case "C":
                result = 3;
                break;
            case "D":
                result = 4;
                break;
            case " ":
                result = nextCellInt;
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }


}
