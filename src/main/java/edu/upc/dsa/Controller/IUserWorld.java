package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import java.util.ArrayList;


public interface IUserWorld {
    Boolean createUser(User usr);
    Boolean deleteUser(int id);
    User queryUser(int id);
    void addItemUser(User u, Item i);
    ArrayList<Item> userItemListQuery(User usr);
    ArrayList<Item> queryUserItemByName(User usr, String itemName);
    void deleteUserItems(User u);
    void userToUserItemTransfer(User origen, User destino, Item i);
}
