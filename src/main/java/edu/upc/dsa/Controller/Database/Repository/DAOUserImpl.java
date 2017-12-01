package edu.upc.dsa.Controller.Database.Repository;

import edu.upc.dsa.Controller.Database.DAO.DAO;
import edu.upc.dsa.Controller.Database.DAO.DAOImpl;
import edu.upc.dsa.Model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static edu.upc.dsa.Controller.Database.DAO.DAOImpl.getInstance;

public abstract class DAOUserImpl implements DAOUser {

    public List<User> selectAllUsers() throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return getInstance().selectAll(User.class);
    }

    public User selectUser(int primaryKey) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u = new User();
        return (User) getInstance().select(u, primaryKey);
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        getInstance().insert(user);
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        getInstance().update(user);

    }

    public void deleteUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        getInstance().delete(user);
    }


}
