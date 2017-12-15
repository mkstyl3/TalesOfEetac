package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.ReflectionException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.UserItem;

import java.sql.SQLException;
import java.util.List;

public interface DAOItem {

    Item selectItem(int primaryKey) throws DAOItemException;
    Item insertItem(Item i) throws DAOItemException;
}
