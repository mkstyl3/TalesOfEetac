package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.*;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserWorldDBImpl implements UserWorldDB {
    final static Logger logger = Logger.getLogger(UserWorldDBImpl.class);
    private static UserWorldDBImpl instance = null;

    public static UserWorldDBImpl getInstance() {
        if (instance == null) instance = new UserWorldDBImpl();
        return instance;
    }

    public User register(User u) throws UserWorldDbException {
        try {
            return DAOImpl.getInstance().insertUser(u);
        } catch (DAOUserException e) {
            throw new UserWorldDbException(e);
        }
    }

    public User login(User userIn) throws UserWorldDbException {
        try {
            return DAOImpl.getInstance().selectUserByUsernameAndPw(userIn.getName(), userIn.getPassword());
        } catch (DAOUserException e) {
            throw new UserWorldDbException(e);
        }
    }

    public List<Item> getItems(int userId) throws DAOUserException {
        try {
            return DAOImpl.getInstance().selectItems(userId, "User");
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public Item getItem(int itemId) throws DAOItemException {
        try {
            return DAOImpl.getInstance().selectItem(itemId);
        }
        catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }

    public boolean deleteUser(User user) throws DAOItemException {
        try {
            DAOImpl.getInstance().delete(user);
            return true;
        }
        catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }

}
