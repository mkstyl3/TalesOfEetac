package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOChestItemException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Relation.ChestItem;

public class DAOChestItemImpl extends DAOImpl {

    public ChestItem insertChestItem(ChestItem cI) throws DAOChestItemException {
        try {
            return (ChestItem) DAOImpl.getInstance().insert(cI);
        } catch (DAOException e) {
            throw new DAOChestItemException(e);
        }
    }
    public boolean deleteChestItems(int chestId) throws DAOChestItemException {
        ChestItem chestItem = new ChestItem();
        try {
            DAOImpl.getInstance().deleteByChestId(chestItem, chestId);
            return true;
        } catch (DAOException e) {
            throw new DAOChestItemException(e);
        }
    }
}