package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class UserWorldTest {
    
    // Loading data

    @Before
    public void setUp() throws Exception {
        UserWorld.getInstance().initializeUsers();
    }

    //Testing exceptions rule

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Test functions

    @Test
    public void createUserTest(){
        //Creating new user
        User new_user = new User(5, "Oriol", "12345678", 37, 30, 30, new Location(8,2));
        Assert.assertTrue(UserWorld.getInstance().createUser(new_user));
        //Creating and existing user
        User new_user_2 = new User(1, "Marc", "1234", 34, 30, 30, new Location(5,4));
        Assert.assertFalse(UserWorld.getInstance().createUser(new_user_2));
    }


    @Test
    public void deleteUserTest() {
        //Deleting existing user
        Assert.assertTrue(UserWorld.getInstance().deleteUser(2));
        Assert.assertNull(UserWorld.getInstance().queryUser(2));
        //Deleting an unexisting user
        Assert.assertFalse(UserWorld.getInstance().deleteUser(6));
    }

    @Test
    public void addItemUser() {
        //Adding item to user
        Item item_5 = new Item(5, "meat", 3, "minor heal", 0.2, 20);
        UserWorld.getInstance().addItemUser(2, item_5);
        Assert.assertTrue(UserWorld.getInstance().getUsersMap().get(2).getItems().contains(item_5));
    }

    @Test
    public void userItemListQueryTest() {
        //user_3 has items
        ArrayList<Item> list_aux = UserWorld.getInstance().userItemListQuery(3);
        Assert.assertEquals(list_aux.get(0).getId(), 1);
        Assert.assertEquals(list_aux.get(1).getId(), 2);

        //user_4 has no items
        Assert.assertTrue(UserWorld.getInstance().userItemListQuery(4).isEmpty());
    }

    @Test
    public void queryUserItemByNameTest() {
        //user_1 have the item_1
        int greaterThanZero = UserWorld.getInstance().queryUserItemByName(UserWorld.getInstance().getUsersMap().get(1), "potion").size();
        Assert.assertTrue("List size must be greater than 0", greaterThanZero > 0);

        //user_2 don't have the item_4
        int zero = UserWorld.getInstance().queryUserItemByName(UserWorld.getInstance().getUsersMap().get(2), "awakening").size();
        Assert.assertTrue("List size must be 0", zero == 0);
    }

    @Test
    public void deleteUserItems() { //////// Ask if it's valid
        //Deleting all items from usr_4
        UserWorld.getInstance().deleteUserItems(UserWorld.getInstance().getUsersMap().get(4));
        Assert.assertTrue(UserWorld.getInstance().getUsersMap().get(4).getItems().isEmpty());
    }

    @Test
    public void userToUserItemTransferTest() { /////// Ask if its valid
        //Transfering item_4 from user_1 to user_2...
        Item origin = UserWorld.getInstance().getUsersMap().get(1).getItem(4);
        UserWorld.getInstance().userToUserItemTransfer(UserWorld.getInstance().getUsersMap().get(1),UserWorld.getInstance().getUsersMap().get(2),origin);
        //Cheking if user_2 has got item_4
        Assert.assertEquals(UserWorld.getInstance().getUsersMap().get(2).getItem(4).getId(),origin.getId());
        //Checking if user_1 has deleted his item_4
        Assert.assertNull(UserWorld.getInstance().getUsersMap().get(1).getItem(4));
    }

}

