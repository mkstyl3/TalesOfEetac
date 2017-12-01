package edu.upc.dsa.Controller.Database.DAO;

import edu.upc.dsa.Controller.Database.Repository.DAOUser;
import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAOImpl implements DAOUser {


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

    public void insert(Object object) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        String query = getInsertQuery(object);
        Connection con = getConnection();

        PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        addClassFieldsParameters(object, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) setField(generatedKeys.getInt(1), "id", object);
        preparedStatement.close();
        con.close();
    }


    public Object select(Object object, int primaryKey) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String query = getSelectQuery(object);
        Connection con = getConnection();

        PreparedStatement preparedStatement = con.prepareStatement(query);
        int position = 1;
        addPrimaryKeyParameter(preparedStatement, position, primaryKey);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        while (resultSet.next()) {
            setFieldsFromResultSet(resultSet, resultSetMetaData, object);
        }
        resultSet.beforeFirst();
        if(!resultSet.next()){
            object = null;
        }
        preparedStatement.close();
        con.close();

        return object;
    }

    public void update(Object object) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String query = getUpdateQuery(object);
        Connection con = getConnection();

        PreparedStatement preparedStatement = con.prepareStatement(query);
        addClassFieldsParameters(object, preparedStatement);
        int primaryKey = getPrimaryKeyParameter(object);
        List<Field> nonObjectDeclaredFields = getNonGenericObjectDeclaredFields(object);
        int position = (nonObjectDeclaredFields.size() + 1);
        addPrimaryKeyParameter(preparedStatement, position, primaryKey);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.close();
    }

    public void delete(Object object) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String query = getDeleteQuery(object);
        Connection con = getConnection();

        PreparedStatement preparedStatement = con.prepareStatement(query);
        int position = 1;
        int primaryKey = getPrimaryKeyParameter(object);
        addPrimaryKeyParameter(preparedStatement, position, primaryKey);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.close();
    }

    public List selectAll(Class classToLoad) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        List<Object> objects = new ArrayList<>();
        String query = getSelectAllQuery(classToLoad);
        Connection con = getConnection();

        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Object newObject = classToLoad.newInstance();
            setFieldsFromResultSet(resultSet, resultSetMetaData, newObject);
            objects.add(newObject);
        }
        preparedStatement.close();
        con.close();
        return objects;
    }

    public String getInsertQuery(Object object) {
        StringBuffer query = new StringBuffer("INSERT INTO ");
        query.append(object.getClass().getSimpleName());
        query.append(" (");
        addFieldsInsertQuery(object, query);
        query.append(")");
        query.append(" VALUES (");
        addInterrogantsInsertQuery(object, query);
        query.append(")");
        return query.toString();
    }

    public String getSelectQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getUpdateQuery(Object object) {
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(object.getClass().getSimpleName());
        query.append(" SET ");
        addFieldsAndInterrogantsUpdateQuery(object, query);
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getDeleteQuery(Object object) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");
        return query.toString();
    }

    public String getSelectAllQuery(Class classToLoad) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(classToLoad.getSimpleName());
        return query.toString();
    }

    private void addFieldsInsertQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName()).append(",");
        } query.deleteCharAt(query.length() - 1);
    }

    private void addInterrogantsInsertQuery(Object object, StringBuffer query) {
        for (Field ignore : getNonGenericObjectDeclaredFields(object)) {
            query.append("?,");
        } query.deleteCharAt(query.length() - 1);
    }

    public void addClassFieldsParameters(Object object, PreparedStatement pstm) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        int i = 1;
        for (Field field : getNonGenericObjectDeclaredFields(object)) {
            Method method = object.getClass().getMethod(getGetterName(field.getName()));
            Object methodObjectResulted = getMethodObjectResulted(object, method, field);
            pstm.setObject(i, methodObjectResulted);
            i++;
        }
    }

    private Object getMethodObjectResulted(Object object, Method method, Field field) throws InvocationTargetException, IllegalAccessException {
        Object methodObjectResulted = null;
        if (field.getType() == Calendar.class) {
            Calendar calendar = (Calendar) method.invoke(object);
            methodObjectResulted = new java.sql.Date(calendar.getTime().getTime());
        } else {
            methodObjectResulted = method.invoke(object);
        } return methodObjectResulted;
    }

    public List<Field> getNonGenericObjectDeclaredFields(Object object) {
        List<Field> nonGenericObjectDeclaredFields = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getType() == String.class || field.getType() == Integer.class || field.getType() == int.class || field.getType() == boolean.class || field.getType() == Boolean.class || field.getType() == Double.class || field.getType() == double.class || field.getType() == Calendar.class) {
                nonGenericObjectDeclaredFields.add(field);
            }
        } return nonGenericObjectDeclaredFields;
    }

    private String getGetterName(String fieldName) {
        StringBuilder getterName = new StringBuilder("get");
        getterName.append(capitalizeName(fieldName));
        return getterName.toString();
    }

    private String getSetterName(String fieldName) {
        StringBuilder setterName = new StringBuilder("set");
        setterName.append(capitalizeName(fieldName));
        return setterName.toString();
    }

    private String capitalizeName(String name) {
        String capitalizedFieldName;
        capitalizedFieldName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalizedFieldName;
    }

    private void addFieldsAndInterrogantsUpdateQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName());
            query.append("=?,");
        } query.deleteCharAt(query.length() - 1);
    }

    public void addPrimaryKeyParameter(PreparedStatement pstm, int position, int primaryKey) throws SQLException {
        pstm.setObject(position, primaryKey);
    }

    public int getPrimaryKeyParameter(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method;
        int id = 0;
        method = object.getClass().getMethod(getGetterName("id"));
        Object methodObjectResulted = method.invoke(object);
        id = Integer.parseInt(methodObjectResulted.toString());
        return id;
    }

    public void setFieldsFromResultSet(ResultSet resultSet, ResultSetMetaData resultSetMetaData, Object object) throws SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnType = resultSetMetaData.getColumnTypeName(i);
            String columnName = resultSetMetaData.getColumnLabel(i);
            switch (columnType) {
                case "VARCHAR":
                    String resultString = resultSet.getString(i);
                    if (resultString != null) {
                        setField(resultString, columnName, object);
                    }
                    break;
                case "INT":
                    int resultInt = resultSet.getInt(i);
                    setField(resultInt, columnName, object);
                    break;
                case "DOUBLE":
                    double resultDouble = resultSet.getDouble(i);
                    setField(resultDouble, columnName, object);
                    break;
                case "TINYINT":
                    boolean resultBoolean = resultSet.getBoolean(i);
                    setField(resultBoolean, columnName, object);
                    break;
                case "DATETIME":
                    java.util.Date resultDate = resultSet.getDate(i);
                    Calendar resultCalendar = Calendar.getInstance();
                    resultCalendar.setTime(resultDate);
                    setField(resultCalendar, columnName, object);
                    break;
                default:
                    break;
            }
        }
    }

    public void setField(Object result, String name, Object object) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Method method;
        method = object.getClass().getMethod(getSetterName(name), object.getClass().getDeclaredField(name).getType());
        method.invoke(object, result);
    }

    @Override
    public User selectUser(int primaryKey) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User u = new User();
        return (User)select(u, primaryKey);
    }
    @Override
    public List<User> selectAllUsers() throws ClassNotFoundException, SQLException, InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        return selectAll(User.class);
    }

    @Override
    public void insertUser(User user) throws SQLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        insert(user);
    }
    @Override
    public void updateUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        update(user);

    }
    @Override
    public void deleteUser(User user) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        delete(user);
    }
}