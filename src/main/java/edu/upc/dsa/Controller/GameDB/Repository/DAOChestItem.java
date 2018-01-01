package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.ExceptionHandler.DAOChestItemException;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.Model.Relation.ChestItem;
import edu.upc.dsa.Model.Relation.UserItem;

public interface DAOChestItem {
    ChestItem insertChestItem(ChestItem cI) throws DAOUserItemException, DAOChestItemException;
}
