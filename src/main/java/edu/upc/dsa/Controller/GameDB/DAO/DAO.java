package edu.upc.dsa.Controller.GameDB.DAO;

import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.ReflectionException;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.UserItem;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    Object insert(Object object) throws DAOException;
    Object select(Object object, int primaryKey) throws DAOException;
    Object selectByName(Object object, String name) throws DAOException;
    Object selectByUsernameAndPw(Object object, String username, String password) throws DAOException;
    void update(Object object) throws DAOException;
    void delete(Object object) throws DAOException;
    Item insertItem(Item i) throws DAOItemException;
    List<Item> selectItems(int userId, String fromUserOrFromChest) throws DAOException;
}
