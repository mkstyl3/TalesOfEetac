package edu.upc.dsa.Controller;


import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.User;

import java.awt.peer.WindowPeer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ConsoleGameScreen {

    private static ConsoleGameScreen instance = null;
    public static ConsoleGameScreen getInstance() {
        if (instance == null) instance = new ConsoleGameScreen();
        return instance;
    }

    String lastCharacterVisited;

    public void initialUserLocation() {
        lastCharacterVisited = World.getInstance().getHashOfMaps().get("54");
        World.getInstance().getHashOfMaps().put("54", "@");
    }

    public int moveLeft (User u) {
        String currentLocation = u.getLocation().getCurrentLocation();
        int leftAnalysisResult = analyzeLeftCell(currentLocation);
        int canOrW0t = 0;
        switch (leftAnalysisResult) {
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
                canOrW0t = 4;
                break;
            default: //can move
                String leftAnalysisResultString = String.valueOf(leftAnalysisResult);
                //I get the character where my char was.
                String lastCharacterVisited = this.lastCharacterVisited;
                //I get the character where my char has to be.
                String newCharacterToVisit = World.getInstance().getHashOfMaps().get(leftAnalysisResultString);
                //I save the new user location.
                u.setLocation(new Location(u.getLocation().getCurrentLocation(), leftAnalysisResultString));
                //I replace old character by what it was.
                World.getInstance().getHashOfMaps().put(currentLocation, lastCharacterVisited);
                //I save the location where @ was to lastCharacterVisited
                this.lastCharacterVisited = newCharacterToVisit;
                //I replace de left-character with @
                World.getInstance().getHashOfMaps().put(leftAnalysisResultString, "@");
        }

        return canOrW0t;
    }

    public int analyzeLeftCell (String cell) {

        int cellInt = Integer.valueOf(cell);
        int result=0;

        if (!cell.substring(1).equals(0)) {
            int leftCellInt = cellInt -1;
            String leftCell = String.valueOf(leftCellInt);
            switch (World.getInstance().getHashOfMaps().get(leftCell)) {
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
                case ",":case ".":
                    result = leftCellInt;
                    break;
            }
        }

        return result;
    }


}
