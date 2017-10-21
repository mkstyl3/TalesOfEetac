package edu.upc.dsa.Model;

public class Location {
    private String currentLocation;

    public Location(String lastLocation, String currentLocation) {
        this.currentLocation = currentLocation;
        this.lastLocation = lastLocation;
    }

    public Location (){ }

    private String lastLocation;

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }


}
