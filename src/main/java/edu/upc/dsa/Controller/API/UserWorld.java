package edu.upc.dsa.Controller.API;

import edu.upc.dsa.Model.Main.User;


public interface UserWorld {
    Boolean set(User usr);
    Boolean del(int id);
    User get(int id);
    //boolean setItem(int userId, Item i);
    //List<Item> getItems(int id);
    //List<Item> getItemByName(int userId, String itemName);
    //boolean delItems(int userid);
    //boolean transferItem(User origen, User destino, Item i);
}
