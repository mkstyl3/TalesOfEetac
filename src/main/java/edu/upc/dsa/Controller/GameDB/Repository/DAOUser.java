package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    List<User> selectAllUsers() throws ClassNotFoundException, SQLException, InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException;
    void insertUser(User user)throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    void updateUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    void deleteUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    User selectUser(int primaryKey) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
