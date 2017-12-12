package edu.upc.dsa.Controller.API;

import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.Model.Relation.UserItem;

public interface UserWorldDB {
     User login(User user) throws UserWorldDbException;
     User register(User u) throws UserWorldDbException;
     Item getItem(int itemId) throws UserWorldDbException, DAOItemException;

}
