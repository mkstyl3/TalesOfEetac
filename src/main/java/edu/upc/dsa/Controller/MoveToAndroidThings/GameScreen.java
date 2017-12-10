package edu.upc.dsa.Controller.MoveToAndroidThings;

import edu.upc.dsa.Model.Main.Location;
import edu.upc.dsa.Model.Main.Map;
import edu.upc.dsa.Model.Main.User;

public interface GameScreen {
    void objectInitializations ();
    Map createMap (int mapId);
    void setMap (Map map);
    void initialUserLocation(User u);
    void moveUserTo (User u, String direction);
    void locateUserAtNextDoorLocation(Location currentDoorLoc, User u);
}
