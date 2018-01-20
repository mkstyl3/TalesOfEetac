package edu.upc.dsa.Controller.API;

import edu.upc.dsa.ExceptionHandler.ChestWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;

import java.util.List;

public interface ChestWorldDB {
    boolean setChest(Chest c) throws ChestWorldDbException;
    List<Item> getItems(int chestId) throws DAOChestException;

}
