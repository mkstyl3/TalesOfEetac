package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.UserItem;

public abstract class DAOItemImpl implements DAOItem {

    public Item insertUserItem(UserItem userItem) throws DAOItemException {
        try{
            return (Item)DAOImpl.getInstance().insert(userItem);
        }
        catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }

    public Item selectItem(int primaryKey) throws DAOItemException {
        try{
            Item i = new Item();
            return (Item)DAOImpl.getInstance().select(i, primaryKey);
        }
        catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }
}
