package edu.upc.dsa.Controller;


public class ConsoleGameScreen {
    private static ConsoleGameScreen instance = null;

    public static ConsoleGameScreen getInstance() {
        if (instance == null) instance = new ConsoleGameScreen();
        return instance;
    }
    public void locateUser(int location) {
        World.getInstance().getHashOfMaps().replace(location, "@"); // Comenzar aki
    }
}
