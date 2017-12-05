package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class UserWorldTest {

    // Loading data

    /*@Before
    public void setUp() throws Exception {
        UserWorld.getInstance().initializeUsers();
    }

    //Testing exceptions rule

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Test functions

    @Test
    public void setTest(){
        //Creating new user
        User new_user = new User(5, "Oriol", "12345678", 37, 30, 30, new Location(8,2));
        Assert.assertTrue(UserWorld.getInstance().set(new_user));
        //Creating and existing user
        User new_user_2 = new User(1, "Marc", "1234", 34, 30, 30, new Location(5,4));
        Assert.assertFalse(UserWorld.getInstance().set(new_user_2));
    }


    @Test
    public void delTest() {
        //Deleting existing user
        Assert.assertTrue(UserWorld.getInstance().del(2));
        Assert.assertNull(UserWorld.getInstance().get(2));
        //Deleting an unexisting user
        Assert.assertFalse(UserWorld.getInstance().del(6));
    }

    @Test
    public void addItem() {
        //Adding item to user
        Item item_5 = new Item(5, "meat", 3, "minor heal", 0.2, 20);
        UserWorld.getInstance().setItem(2, item_5);
        Assert.assertTrue(UserWorld.getInstance().getItems(2).contains(item_5));
    }

    @Test
    public void getItemsTest() {
        //user_3 has items
        List<Item> list_aux = UserWorld.getInstance().getItems(3);
        Assert.assertEquals(list_aux.get(0).getId(), 1);
        Assert.assertEquals(list_aux.get(1).getId(), 2);

        //user_4 has no items
        Assert.assertTrue(UserWorld.getInstance().getItems(4).isEmpty());
    }

    @Test
    public void getItemByNameTest() {
        //user_1 have the item_1
        Assert.assertNotNull(UserWorld.getInstance().getItemByName(1, "potion"));

        //user_1 don't have item called "awakening"
        Assert.assertNull(UserWorld.getInstance().getItemByName(1, "awakening"));
    }

    @Test
    public void delItems() {
        //Deleting all items from usr_4
        UserWorld.getInstance().delItems(4);
        Assert.assertTrue(UserWorld.getInstance().getItems(4).isEmpty());
    }

    @Test
    public void itemTransferTest() { //LOCAL, ONLY FOR EXAM PURPOSES
        //inicialization
        User u1 = new User();
        User u2 = new User();
        Item i = new Item();
        u1.setItem(i);

        //Test
        Assert.assertTrue(UserWorld.getInstance().transferItem(u1,u2,i));
        //u1 item list is empty?
        Assert.assertTrue(u1.getItems().isEmpty());
        //u2 has the item i?
        Assert.assertNotNull(u2.getItems().get(0));
    }*/

}

