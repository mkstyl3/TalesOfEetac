package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.Model.Relation.UserItem;

public interface DAOUserItem {
    UserItem insertUserItem(UserItem uI) throws DAOUserItemException;
}
