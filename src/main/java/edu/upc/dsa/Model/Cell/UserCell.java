package edu.upc.dsa.Model.Cell;

import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.User;

public class UserCell extends Cell {

    final protected String symbol = "@";

    private User u;

    public UserCell () {
        u = new User();
    }

    public UserCell (User u) {
        this.u = new User ();
        this.u = u;
        super.setOnMapLoc(u.getLocation());
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String getSYMBOL() {
        return this.symbol;
    }

}
