package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class FuncionsDatabase {

    private static FuncionsDatabase instance = null;

    public static FuncionsDatabase getInstance() {
        if (instance == null) instance = new FuncionsDatabase();
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



}
