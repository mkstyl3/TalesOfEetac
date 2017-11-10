package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import java.util.ArrayList;
import java.util.List;


public interface IUserWorld {
    Boolean set(User usr);
    Boolean del(int id);
    User get(int id);
    boolean setItem(int userId, Item i);
    List<Item> getItems(int id);
    List<Item> getItemByName(int userId, String itemName);
    boolean delItems(int userid);
    boolean transferItem(User origen, User destino, Item i);
}
