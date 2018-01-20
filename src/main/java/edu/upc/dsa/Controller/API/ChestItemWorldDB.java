package edu.upc.dsa.Controller.API;

import edu.upc.dsa.ExceptionHandler.ChestItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Relation.ChestItem;

public interface ChestItemWorldDB {
    boolean setChestItem(ChestItem cI) throws ChestItemWorldDbException;
    boolean deleteChestItems(int chestId) throws DAOException;
}
