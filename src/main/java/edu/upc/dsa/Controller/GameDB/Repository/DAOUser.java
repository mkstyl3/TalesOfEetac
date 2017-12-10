package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import java.util.List;

public interface DAOUser {
    List selectAllUsers() throws DAOUserException;
    User insertUser(User user)throws DAOUserException;
    void updateUser(User user) throws DAOUserException;
    void deleteUser(User user) throws DAOUserException;
    User selectUser(int primaryKey) throws DAOUserException;
    User selectUserByUsernameAndPw (int primaryKey) throws DAOUserException;
    User selectUserByUsername (String username) throws DAOUserException;
}
