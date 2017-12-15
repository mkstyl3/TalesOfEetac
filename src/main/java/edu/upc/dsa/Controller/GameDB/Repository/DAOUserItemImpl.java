package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.UserItem;

import java.util.ArrayList;

public class DAOUserItemImpl extends DAOImpl {

    public UserItem insertUserItem(UserItem uI) throws DAOUserItemException {
        try {
            return (UserItem) DAOImpl.getInstance().insert(uI);
        } catch (DAOException e) {
            throw new DAOUserItemException(e);
        }
    }


}
