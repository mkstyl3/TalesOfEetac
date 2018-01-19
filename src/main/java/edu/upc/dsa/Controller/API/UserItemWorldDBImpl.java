package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.Model.Relation.UserItem;
import org.apache.log4j.Logger;

public class UserItemWorldDBImpl {

    //Variable declarations

    final static Logger logger = Logger.getLogger(UserItemWorldDBImpl.class);
    private static UserItemWorldDBImpl instance = null;

    public static UserItemWorldDBImpl getInstance() {
        if (instance == null) instance = new UserItemWorldDBImpl();
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
