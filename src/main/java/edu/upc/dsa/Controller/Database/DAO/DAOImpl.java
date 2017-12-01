package edu.upc.dsa.Controller.Database.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DAOImpl {


    private static DAOImpl instance = null;

    public static DAOImpl getInstance() {
        if (instance == null) instance = new DAOImpl();
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
    public void insert(Object object) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        String query = getInsertQuery(object);
        Connection con = getConnection();
        /*
        PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        addClassFieldsParameters(object, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) setField(generatedKeys.getInt(1), "id", object);
        preparedStatement.close();
        con.close();
    }

    //Private functions

    private String getInsertQuery(Object o)*/




}