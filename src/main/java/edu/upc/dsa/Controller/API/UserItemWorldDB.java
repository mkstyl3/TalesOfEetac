package edu.upc.dsa.Controller.API;

import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.Model.Relation.UserItem;

public interface UserItemWorldDB {
    boolean setUserItem(UserItem uI) throws UserItemWorldDbException;
}
