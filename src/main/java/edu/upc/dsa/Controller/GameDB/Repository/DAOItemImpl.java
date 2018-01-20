package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.Model.Relation.UserItem;

public abstract class DAOItemImpl extends DAOImpl {

    public Item insertItem(Item i) throws DAOItemException {
        try{
            return (Item)DAOImpl.getInstance().insert(i);
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

    public void deleteItem(Item item) throws DAOItemException {
        try {
            getInstance().delete(item);
        }
        catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }
}
