package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.UserItem;
import org.apache.log4j.Logger;

public class UserItemWorldDbImpl {

    //Variable declarations

    final static Logger logger = Logger.getLogger(UserItemWorldDbImpl.class);
    private static UserItemWorldDbImpl instance = null;

    public static UserItemWorldDbImpl getInstance() {
        if (instance == null) instance = new UserItemWorldDbImpl();
        return instance;
    }

    public boolean setUserItem(UserItem uI) throws UserItemWorldDbException {
        try {
            DAOImpl.getInstance().insertUserItem(uI);
            return true;
        } catch (DAOUserItemException e) {
            throw new UserItemWorldDbException(e);
        }
    }
}
