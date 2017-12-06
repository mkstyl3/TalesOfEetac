package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Model.User;
import edu.upc.dsa.View.ExceptionHandling.DAOException;
import edu.upc.dsa.View.ExceptionHandling.DAOUserException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static edu.upc.dsa.Controller.GameDB.DAO.DAOImpl.getInstance;

public abstract class DAOUserImpl implements DAOUser {

    public List<User> selectAllUsers() throws DAOUserException {
        try {
            return getInstance().selectAll(User.class);
        }
        catch (DAOException e) {
            throw new DAOUserException("DAO User level",e );
        }
    }

    public User selectUser(int primaryKey) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().select(u, primaryKey);
        }
        catch (DAOException e) {
            throw new DAOUserException("DAO User level",e );
        }
    }

    public void insertUser(User user) throws DAOUserException {
        try {
            getInstance().insert(user);
        }
        catch (DAOException e) {
            throw new DAOUserException("DAO User level",e );
        }

    }

    public void updateUser(User user) throws DAOUserException {
        try {
            getInstance().update(user);
        }
        catch (DAOException e) {
            throw new DAOUserException("DAO User level",e );
        }
    }

    public void deleteUser(User user) throws DAOUserException {
        try {
            getInstance().delete(user);
        }
        catch (DAOException e) {
            throw new DAOUserException("DAO User level",e );
        }

    }
}
