package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import edu.upc.dsa.ExceptionHandler.ItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import org.apache.log4j.Logger;

public class ItemWorldDBImpl implements ItemWorldDB {
    //Variable declarations

    final static Logger logger = Logger.getLogger(ItemWorldDBImpl.class);
    private static ItemWorldDBImpl instance = null;

    public static ItemWorldDBImpl getInstance() {
        if (instance == null) instance = new ItemWorldDBImpl();
        return instance;
    }

    public boolean setItem(Item i) throws ItemWorldDbException {
        try {
            DAOImpl.getInstance().insertItem(i);
            return true;
        } catch (DAOItemException e) {
            throw new ItemWorldDbException(e);
        }
    }


}
