package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class UserWorldDBImpl{
    final static Logger logger = Logger.getLogger(UserWorldDBImpl.class);
    private static UserWorldDBImpl instance = null;

    public static UserWorldDBImpl getInstance() {
        if (instance == null) instance = new UserWorldDBImpl();
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Connection con = null;
        //com.mysql.cj.jdbc.Driver, lo detecta automaticamente
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/daodb", "root", "1234");
        System.out.println("Connected to database");
        return con;
    }

    /*
    public String GuardarQuery (User user) {

        StringBuffer query = new StringBuffer("UPDATE users SET ");
        query.append("jamones=");
        query.append(user.getItem("jamones"));
        query.append(" , ");
        query.append("drogas=");
        query.append(user.getItem("drogas"));
        query.append(" , ");
        query.append("cable_ethernet=");
        query.append(user.getItem("cable_ethernet"));
        query.append(" , ");
        query.append("x=");
        query.append(user.getLocation().getX());
        query.append(" , ");
        query.append("y=");
        query.append(user.getLocation().getY());
        query.append(" WHERE id=");
        query.append(user.getId());
        return query.toString();
    }

    public void Guardar (User user) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        String query = GuardarQuery(user);
        Connection con = getConnection();

        con.prepareStatement(query).execute();


        con.close();
    }

    public String RegistrarQuery (User user){

        StringBuffer query = new StringBuffer("INSERT INTO users (id,nom,password,jamones,drogas,cable_ethernet,x,y) VALUES ( ");
        query.append(user.getId());
        query.append(" , '");
        query.append(user.getUsername());
        query.append("' , '");
        query.append(user.getPassword());
        query.append("' , 0 , 0 , 0 , 0 , 0)");
        return query.toString();

    }


    /*
    private boolean doExist(String username) throws UserWorldDbException {
        logger.info("doExist: Checking if user with username: " + username + " exists...");
        boolean doExist = false;

        try {
            if (DAOImpl.getInstance().selectUserByUsername(username) != null) {
                logger.info("doExist: User with username: " + username + " already exists");
                doExist = true;
            } else {
                logger.info("doExist: User with username: " + username + " doesn't exist");
            }
        }
        catch (DAOUserException e) {
            throw new UserWorldDbException(e);
        }

        return doExist;
    }
    */

    public User register(User u) throws UserWorldDbException {
        try {
            return DAOImpl.getInstance().insertUser(u);
        }

        catch (DAOUserException e) {
            throw new UserWorldDbException(e);
        }
    }

    public User login(User userIn) throws UserWorldDbException {
        try {
            return DAOImpl.getInstance().selectUserByUsernameAndPw(userIn.getUsername(), userIn.getPassword());
        }
        catch (DAOUserException e) {
            throw new UserWorldDbException(e);
        }
    }
}
