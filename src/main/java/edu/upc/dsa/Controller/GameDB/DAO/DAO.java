package edu.upc.dsa.Controller.GameDB.DAO;

import java.util.List;

public interface DAO {
    void insert(Object object);
    void select(Object object, int primaryKey);
    void update(Object object);
    void delete(Object object);
    List selectAll(Class classToLoad);
}
