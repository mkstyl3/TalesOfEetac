package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.Map;
import edu.upc.dsa.Model.User;

public interface IGameScreenWorld {
    void objectInitializations ();
    Map createMap (int mapId);
    void loadMap (Map map);
    void initialUserLocation(User u);
    void moveUserTo (User u, String direction);
    void locateUserAtNextDoorLocation(Location currentDoorLoc, User u);
}
