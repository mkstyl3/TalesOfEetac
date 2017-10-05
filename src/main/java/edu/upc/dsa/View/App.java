package edu.upc.dsa.View;

import edu.upc.dsa.Controller.World;
import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;

/**
 * First version!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        User usr_1 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        User usr_2 = new User(2, "Gerard", "1234", 34, 30, 30, 30);
        User usr_3 = new User(3, "Ivan", "1234", 34, 30, 30, 30);
        User usr_4 = new User(4, "Mario", "1234", 34, 30, 30, 30);

        Item item_1 = new Item(1,"potion", 3, "minor heal", 0.2, 20);
        Item item_2 = new Item(2,"paralize", 3, "minor heal", 0.2, 20);
        Item item_3 = new Item(3,"mana", 3, "minor heal", 0.2, 20);
        Item item_4 = new Item(4,"speed", 3, "minor heal", 0.2, 20);

        World.getInstance().createUser(usr_1);
        World.getInstance().createUser(usr_2);
        World.getInstance().createUser(usr_3);
        World.getInstance().createUser(usr_4);

        World.getInstance().deleteUser(2);

        World.getInstance().addItemUser(usr_1, item_1);
        World.getInstance().deleteUserItems(usr_3);
        World.getInstance().queryUserItem(usr_2, "healing");


    }
}
