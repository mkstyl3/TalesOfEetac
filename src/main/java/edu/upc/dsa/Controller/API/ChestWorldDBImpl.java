package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.ChestWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;
import org.apache.log4j.Logger;

import java.util.List;

public class ChestWorldDBImpl implements ChestWorldDB {
    //Variable declarations

    final static Logger logger = Logger.getLogger(ChestWorldDBImpl.class);
    private static ChestWorldDBImpl instance = null;

    public static ChestWorldDBImpl getInstance() {
        if (instance == null) instance = new ChestWorldDBImpl();
        return instance;
    }

    public boolean setChest(Chest c) throws ChestWorldDbException {
        try {
            DAOImpl.getInstance().insertChest(c);
            return true;
        } catch (DAOChestException e) {
            throw new ChestWorldDbException(e);
        }
    }

    public List<Item> getItems(int chestId) throws DAOChestException {
        try {
            return DAOImpl.getInstance().selectItems(chestId, "Chest");
        }
        catch (DAOException e) {
            throw new DAOChestException(e);
        }
    }
}
