package edu.upc.dsa.Controller.API;

import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.ItemWorldDbException;
import edu.upc.dsa.Model.Main.Item;

public interface ItemWorldDB {
    boolean setItem(Item i) throws ItemWorldDbException;
    boolean deleteItem(Item item) throws ItemWorldDbException;
}
