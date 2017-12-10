package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.Controller.ExceptionHandler.DAOException;
import edu.upc.dsa.Controller.ExceptionHandler.DAOUserException;
import java.util.List;

import static edu.upc.dsa.Controller.GameDB.DAO.DAOImpl.getInstance;

public abstract class DAOUserImpl implements DAOUser {

    public List<User> selectAllUsers() throws DAOUserException {
        try {
            return getInstance().selectAll(User.class);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

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
        u.setUsername(username);
        try {
            return (User)getInstance().selectByName(u, username);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public User selectUserByUsernameAndPw(int userId, String password) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().selectByUsernameAndPw(u, userId, password);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    public User insertUser(User user) throws DAOUserException {
        User v;
        try {
            v = (User)getInstance().insert(user);
        }
        catch (DAOException e) {
            throw new DAOUserException(e);
        }
        return v;
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
