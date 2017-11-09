package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import java.util.ArrayList;


public interface IUserWorld {
    Boolean createUser(User usr);
    Boolean deleteUser(int id);
    User queryUser(int id);
    boolean addItemUser(int userId, Item i);
    ArrayList<Item> userItemListQuery(int id);
    ArrayList<Item> queryUserItemByName(User usr, String itemName);
    boolean deleteUserItems(User u);
    boolean userToUserItemTransfer(User origen, User destino, Item i);
}
