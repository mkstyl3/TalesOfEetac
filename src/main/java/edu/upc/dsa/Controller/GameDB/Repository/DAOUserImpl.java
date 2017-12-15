package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import edu.upc.dsa.Model.Relation.UserItem;

import java.util.ArrayList;
import java.util.List;

import static edu.upc.dsa.Controller.GameDB.DAO.DAOImpl.getInstance;

public abstract class DAOUserImpl extends DAOImpl {

    /*public List<User> selectAllUsers() throws DAOUserException {
        try {
            return getInstance().selectAll(User.class);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }*/

    public User selectUser(int primaryKey) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().select(u, primaryKey);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public User selectUserByUsername (String username) throws DAOUserException {
        User u = new User();
        u.setName(username);
        try {
            return (User)getInstance().selectByName(u, username);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public User selectUserByUsernameAndPw(String username, String password) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().selectByUsernameAndPw(u, username, password);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public User insertUser(User user) throws DAOUserException {
        try {
            return (User)getInstance().insert(user);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public void updateUser(User user) throws DAOUserException {
        try {
            getInstance().update(user);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public void deleteUser(User user) throws DAOUserException {
        try {
            getInstance().delete(user);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }
}
