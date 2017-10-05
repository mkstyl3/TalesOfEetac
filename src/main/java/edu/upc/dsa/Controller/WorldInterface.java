package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;

import java.util.ArrayList;
import java.util.List;

public interface WorldInterface {
    Boolean createUser(User usr);
    Boolean deleteUser(int id);
    User userQuery(int id);
    void addItemUser(User u, Item i);
    List<Item> userItemListQuery(User usr);
    ArrayList<Item> queryUserItem(User usr, String itemName);
    void deleteUserItems(User u);
    void userToUserItemTransfer(User origen, User destino, Item i);
}
