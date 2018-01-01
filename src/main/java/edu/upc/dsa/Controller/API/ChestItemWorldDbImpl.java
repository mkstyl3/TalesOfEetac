package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.ChestItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOChestItemException;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Relation.ChestItem;
import edu.upc.dsa.Model.Relation.UserItem;
import org.apache.log4j.Logger;

public class ChestItemWorldDbImpl {

    //Variable declarations

    final static Logger logger = Logger.getLogger(ChestItemWorldDbImpl.class);
    private static ChestItemWorldDbImpl instance = null;

    public static ChestItemWorldDbImpl getInstance() {
        if (instance == null) instance = new ChestItemWorldDbImpl();
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
}
