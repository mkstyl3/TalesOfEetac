package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class WorldTest {

    //Global class variables

    User usr_1;
    User usr_2;
    User usr_3;
    User usr_4;

    Item item_1;
    Item item_2;
    Item item_3;
    Item item_4;

    // Loading data

    //Private function



    @Before
    public void setUp() throws Exception {

        usr_1 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        usr_2 = new User(2, "Gerard", "12345", 35, 30, 30, 30);
        usr_3 = new User(3, "Ivan", "123456", 36, 30, 30, 30);
        usr_4 = new User(4, "Mario", "1234567", 37, 30, 30, 30);

        item_1 = new Item(1, "potion", 3, "minor heal", 0.2, 20);
        item_2 = new Item(2, "paralize", 3, "minor paralize", 0.2, 20);
        item_3 = new Item(3, "mana", 3, "great mana", 0.2, 20);
        item_4 = new Item(4, "speed", 3, "awakening", 0.2, 20);

        usr_1.setItem(item_1);
        usr_1.setItem(item_2);
        usr_1.setItem(item_3);
        usr_1.setItem(item_4);

        usr_2.setItem(item_1);
        usr_2.setItem(item_2);
        usr_2.setItem(item_3);

        usr_3.setItem(item_1);
        usr_3.setItem(item_2);

        // usr_4 has no items

        World.getInstance().usersMap.put(usr_1.getId(), usr_1);
        World.getInstance().usersMap.put(usr_2.getId(), usr_2);
        World.getInstance().usersMap.put(usr_3.getId(), usr_3);
        World.getInstance().usersMap.put(usr_4.getId(), usr_4);
    }

    //Testing exceptions rule

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Test functions

    @Test
    public void userExistTest () {
        //usr_1 does exist
        Assert.assertTrue(World.getInstance().userExist(usr_1.getId()));
        //usr_100 does not exist
        Assert.assertFalse(World.getInstance().userExist(100));
    }

    @Test
    public void createUserTest(){
        //Creating new user
        User new_user = new User(5, "Oriol", "12345678", 37, 30, 30, 30);
        Assert.assertTrue(World.getInstance().createUser(new_user));
        //Creating and existing user
        User new_user_2 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        Assert.assertFalse(World.getInstance().createUser(new_user_2));
    }


    @Test
    public void deleteUserTest() {
        //Deleting existing user
        Assert.assertTrue(World.getInstance().deleteUser(2));
        Assert.assertNull(World.getInstance().queryUser(2));
        //Deleting an unexisting user
        Assert.assertFalse(World.getInstance().deleteUser(6));
    }

    @Test
    public void addItemUser() {
        //Adding item to user
        Item item_5 = new Item(5, "meat", 3, "minor heal", 0.2, 20);
        World.getInstance().addItemUser(usr_2, item_5);
        Assert.assertTrue(World.getInstance().usersMap.get(2).getUserItemList().contains(item_5));
    }

    @Test
    public void userItemListQueryTest() {
        //user_3 has items
        ArrayList<Item> list_aux = World.getInstance().userItemListQuery(usr_3);
        Assert.assertEquals(list_aux.get(0).getId(), item_1.getId());
        Assert.assertEquals(list_aux.get(1).getId(), item_2.getId());

        //user_4 has no items
        Assert.assertTrue(World.getInstance().userItemListQuery(usr_4).isEmpty());
    }

    @Test
    public void queryUserItemByNameTest() {
        //user_1 have the item_1
        int greaterThanZero = World.getInstance().queryUserItemByName(usr_1, "potion").size();
        Assert.assertTrue("List size must be greater than 0", greaterThanZero > 0);

        //user_2 don't have the item_4
        int zero = World.getInstance().queryUserItemByName(usr_2, "awakening").size();
        Assert.assertTrue("List size must be 0", zero == 0);
    }

    @Test
    public void deleteUserItems() {
        //Deleting all items from usr_4
        World.getInstance().deleteUserItems(usr_4);
        Assert.assertTrue(World.getInstance().usersMap.get(4).getUserItemList().isEmpty());
    }

    @Test
    public void userToUserItemTransferTest() {
        //
        Item origin = World.getInstance().usersMap.get(1).getItem(4);
        World.getInstance().userToUserItemTransfer(usr_1,usr_2,origin);

        Assert.assertEquals(World.getInstance().usersMap.get(2).getItem(4).getId(),origin.getId());
        Assert.assertNull(World.getInstance().usersMap.get(1).getItem(4));
    }


}

