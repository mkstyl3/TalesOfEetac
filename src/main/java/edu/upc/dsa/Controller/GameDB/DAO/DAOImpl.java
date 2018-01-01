package edu.upc.dsa.Controller.GameDB.DAO;

import edu.upc.dsa.Controller.GameDB.Repository.*;
import edu.upc.dsa.ExceptionHandler.*;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Main.User;
import edu.upc.dsa.Model.Relation.ChestItem;
import edu.upc.dsa.Model.Relation.UserItem;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAOImpl implements DAOUser, DAOItem, DAOChest, DAOUserItem, DAOChestItem, DAO {

    private static DAOImpl instance = null;

    public static DAOImpl getInstance() {
        if (instance == null) instance = new DAOImpl();

        return instance;
    }

    //General
    public Object insert(Object object) throws DAOException {

        try {
            Connection con = getConnection();
            String query = getInsertQuery(object);

            PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            addClassFieldsParameters(object, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) setField(generatedKeys.getInt(1), "id", object);
            preparedStatement.close();
            con.close();

            return object;
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }

    //General
    public Object select(Object object, int primaryKey) throws DAOException {
        try {
            Connection con = getConnection();
            String query = getSelectQuery(object);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, object);
            }
            resultSet.beforeFirst();
            if (!resultSet.next()) {
                object = null;
            }
            preparedStatement.close();
            con.close();

            return object;
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }

    //General
    public Object selectByName(Object object, String name) throws DAOException {
        try {

            String query = getSelectWithUsernameQuery(object);
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            preparedStatement.setObject(position, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, object);
            }
            resultSet.beforeFirst();
            if (!resultSet.next()) {
                object = null;
            }
            preparedStatement.close();
            con.close();

            return object;
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }

    //Specific
    public List<Item> selectItemsFromUser(int userId, String fromUserOrFromChest) throws DAOException {
        try {
            String query = null;
            Connection con = getConnection();
            List<Item> items = new ArrayList<>();

            if (fromUserOrFromChest.equals("User"))
                 query = getSelectItemsFromUserQuery();

            else if (fromUserOrFromChest.equals("Chest")) {
                query = getSelectItemsFromUserQuery();
            }

            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Item item = new Item();
                setFieldsFromResultSet(resultSet, resultSetMetaData, item);
                items.add(item);
            }
            preparedStatement.close();
            con.close();


            return items;
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }



    public Object selectByUsernameAndPw(Object object, String username, String password) throws DAOException {
        try {
            Connection con = getConnection();
            String query = getSelectWithUsernameAndPwQuery(object);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, object);
            }
            resultSet.beforeFirst();
            if (!resultSet.next()) {
                object = null;
            }
            preparedStatement.close();
            con.close();

            return object;
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }

    /*public List selectAll(Class classToLoad) throws DAOException {
        try {
            Connection con = getConnection();
            List<Object> objects = new ArrayList<>();
            String query = getSelectAllQuery(classToLoad);


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
        } catch (ReflectionException | SQLException | IllegalAccessException | InstantiationException e) {
            throw new DAOException(e);
        }
    }*/


    public void update(Object object) throws DAOException {
        try {
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
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(Object object) throws DAOException {
        try {
            String query = getDeleteQuery(object);
            Connection con = getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(query);
            int position = 1;
            int primaryKey = getPrimaryKeyParameter(object);
            addPrimaryKeyParameter(preparedStatement, position, primaryKey);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (ReflectionException | SQLException e) {
            throw new DAOException(e);
        }
    }


    private Connection getConnection() throws SQLException, ReflectionException {

        //com.mysql.cj.jdbc.Driver, lo detecta automaticamente
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "phpmyadmin@localhost", "phpmyadmin");
            System.out.println("Connected to database");

            return con;
        } catch (ClassNotFoundException e) {
            throw new ReflectionException(e);
        }
    }

    private String getSelectItemsFromUserQuery() {
        StringBuilder query = new StringBuilder("SELECT Item.id, Item.name, Item.type, Item.description, Item.cost ");
        query.append("FROM Item INNER JOIN UserItem ");
        query.append("ON UserItem.itemId = Item.id ");
        query.append("WHERE UserItem.userId = ?");
        return  query.toString();
    }

    private String getSelectItemsFromChestQuery() {
        StringBuilder query = new StringBuilder("SELECT Item.id, Item.name, Item.type, Item.description, Item.cost ");
        query.append("FROM Item INNER JOIN ChestItem ");
        query.append("ON ChestItem.itemId = Item.id ");
        query.append("WHERE ChestItem.userId = ?");
        return  query.toString();
    }

    private String getInsertQuery(Object object) {
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

    private String getSelectQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");

        return query.toString();
    }

    private String getSelectWithUsernameQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE name=?");

        return query.toString();
    }

    private String getSelectWithUsernameAndPwQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE name=?");
        query.append(" AND password=?");

        return query.toString();
    }

    private String getUserItemQuery() {
        StringBuilder query = new StringBuilder("SELECT UserItem.id, itemId, userId ");
        query.append("FROM UserItem ");
        query.append("LEFT JOIN User ON User.id = UserItem.id ");
        query.append("WHERE User.id = ?");
        return query.toString();
    }

    private String getUpdateQuery(Object object) {
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(object.getClass().getSimpleName());
        query.append(" SET ");
        addFieldsAndInterrogantsUpdateQuery(object, query);
        query.append(" WHERE id=?");

        return query.toString();
    }

    private String getDeleteQuery(Object object) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE id=?");

        return query.toString();
    }

    private String getSelectAllQuery(Class classToLoad) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(classToLoad.getSimpleName());

        return query.toString();
    }

    private void addFieldsInsertQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addInterrogantsInsertQuery(Object object, StringBuffer query) {
        for (Field ignore : getNonGenericObjectDeclaredFields(object)) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addClassFieldsParameters(Object object, PreparedStatement pstm) throws SQLException, ReflectionException {
        int i = 1;
        try {
            for (Field field : getNonGenericObjectDeclaredFields(object)) {
                Method method = object.getClass().getMethod(getGetterName(field.getName()));
                Object methodObjectResultant = getMethodObjectResultant(object, method, field);
                pstm.setObject(i, methodObjectResultant);
                i++;
            }
        } catch (NoSuchMethodException e) {
            throw new ReflectionException(e);
        }

    }

    private Object getMethodObjectResultant(Object object, Method method, Field field) throws ReflectionException {
        Object methodObjectResulted = null;
        try {
            if (field.getType() == Calendar.class) {
                Calendar calendar = (Calendar) method.invoke(object);
                methodObjectResulted = new java.sql.Date(calendar.getTime().getTime());
            } else {
                methodObjectResulted = method.invoke(object);
            }
            return methodObjectResulted;
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new ReflectionException(e);
        }

    }

    private List<Field> getNonGenericObjectDeclaredFields(Object object) {
        List<Field> nonGenericObjectDeclaredFields = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getType() == String.class || field.getType() == Integer.class || field.getType() == int.class || field.getType() == boolean.class || field.getType() == Boolean.class || field.getType() == Double.class || field.getType() == double.class || field.getType() == Calendar.class) {
                nonGenericObjectDeclaredFields.add(field);
            }
        }

        return nonGenericObjectDeclaredFields;
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
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addPrimaryKeyParameter(PreparedStatement pstm, int position, int primaryKey) throws SQLException {
        pstm.setObject(position, primaryKey);
    }

    private int getPrimaryKeyParameter(Object object) throws ReflectionException {
        Method method;
        int id = 0;

        try {
            method = object.getClass().getMethod(getGetterName("id"));
            Object methodObjectResulted = method.invoke(object);
            id = Integer.parseInt(methodObjectResulted.toString());

            return id;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new ReflectionException(e);
        }

    }

    private void setFieldsFromResultSet(ResultSet resultSet, ResultSetMetaData resultSetMetaData, Object object) throws ReflectionException, SQLException {
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

    private void setField(Object result, String name, Object object) throws ReflectionException {
        Method method;
        try {
            method = object.getClass().getMethod(getSetterName(name), object.getClass().getDeclaredField(name).getType());
            method.invoke(object, result);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }
    /*
    @Override
    public List<User> selectAllUsers() throws DAOUserException {
        try {
            return getInstance().selectAll(User.class);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }*/

    @Override
    public User selectUser(int primaryKey) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().select(u, primaryKey);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    @Override
    public User selectUserByUsername(String username) throws DAOUserException {
        User u = new User();
        u.setName(username);
        try {
            return (User) getInstance().selectByName(u, username);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    @Override
    public User selectUserByUsernameAndPw(String username, String password) throws DAOUserException {
        User u = new User();
        try {
            return (User) getInstance().selectByUsernameAndPw(u, username, password);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }


    @Override
    public User insertUser(User user) throws DAOUserException {
        try {
            return (User) getInstance().insert(user);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DAOUserException {
        try {
            getInstance().update(user);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DAOUserException {
        try {
            getInstance().delete(user);
        } catch (DAOException e) {
            throw new DAOUserException(e);
        }
    }

    @Override
    public Item selectItem(int primaryKey) throws DAOItemException {
        try {
            Item i = new Item();
            return (Item) DAOImpl.getInstance().select(i, primaryKey);
        } catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }

    @Override
    public Item insertItem(Item i) throws DAOItemException {
        try {
            return (Item) DAOImpl.getInstance().insert(i);
        } catch (DAOException e) {
            throw new DAOItemException(e);
        }
    }

    @Override
    public Chest insertChest(Chest c) throws DAOChestException {
        try {
            return (Chest) DAOImpl.getInstance().insert(c);
        } catch (DAOException e) {
            throw new DAOChestException(e);
        }
    }

    @Override
    public UserItem insertUserItem(UserItem uI) throws DAOUserItemException {
        try {
            return (UserItem) DAOImpl.getInstance().insert(uI);
        } catch (DAOException e) {
            throw new DAOUserItemException(e);
        }
    }
    @Override
    public ChestItem insertChestItem(ChestItem cI) throws DAOChestItemException {
        try {
            return (ChestItem) DAOImpl.getInstance().insert(cI);
        } catch (DAOException e) {
            throw new DAOChestItemException(e);
        }
    }
}