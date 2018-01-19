package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.*;
import edu.upc.dsa.Model.Relation.ChestItem;
import org.apache.log4j.Logger;

public class ChestItemWorldDBImpl {

    //Variable declarations

    final static Logger logger = Logger.getLogger(ChestItemWorldDBImpl.class);
    private static ChestItemWorldDBImpl instance = null;

    public static ChestItemWorldDBImpl getInstance() {
        if (instance == null) instance = new ChestItemWorldDBImpl();
        return instance;
    }

    public boolean setChestItem(ChestItem cI) throws ChestItemWorldDbException {
        try {
            DAOImpl.getInstance().insertChestItem(cI);
            return true;
        } catch (DAOChestItemException e) {
            throw new ChestItemWorldDbException(e);
        }
    }

    public boolean deleteChestItems(int chestId) throws DAOException {
        ChestItem chestItem = new ChestItem();
        try {
            DAOImpl.getInstance().deleteByChestId(chestItem, chestId);
            return true;

        } catch (DAOChestItemException e) {
            throw new ChestItemWorldDbException(e);
        }
    }

}
