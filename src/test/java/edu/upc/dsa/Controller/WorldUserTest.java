package edu.upc.dsa.Controller;

import edu.upc.dsa.Model.Item;
import edu.upc.dsa.Model.User;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class WorldUserTest {

    //Global class variables
   /*
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

        WorldUser.getInstance().getUsersMap().put(usr_1.getId(), usr_1);
        getInstance().getUsersMap().put(usr_2.getId(), usr_2);
        getInstance().getUsersMap().put(usr_3.getId(), usr_3);
        getInstance().getUsersMap().put(usr_4.getId(), usr_4);
    }

    //Testing exceptions rule

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Test functions

    @Test
    public void createUserTest(){
        //Creating new user
        User new_user = new User(5, "Oriol", "12345678", 37, 30, 30, 30);
        Assert.assertTrue(getInstance().createUser(new_user));
        //Creating and existing user
        User new_user_2 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        Assert.assertFalse(getInstance().createUser(new_user_2));
    }


    @Test
    public void deleteUserTest() {
        //Deleting existing user
        Assert.assertTrue(getInstance().deleteUser(2));
        Assert.assertNull(getInstance().queryUser(2));
        //Deleting an unexisting user
        Assert.assertFalse(getInstance().deleteUser(6));
    }

    @Test
    public void addItemUser() {
        //Adding item to user
        Item item_5 = new Item(5, "meat", 3, "minor heal", 0.2, 20);
        getInstance().addItemUser(usr_2, item_5);
        Assert.assertTrue(getInstance().getUsersMap().get(2).getItemList().contains(item_5));
    }

    @Test
    public void userItemListQueryTest() {
        //user_3 has items
        ArrayList<Item> list_aux = getInstance().userItemListQuery(usr_3);
        Assert.assertEquals(list_aux.get(0).getId(), item_1.getId());
        Assert.assertEquals(list_aux.get(1).getId(), item_2.getId());

        //user_4 has no items
        Assert.assertTrue(getInstance().userItemListQuery(usr_4).isEmpty());
    }

    @Test
    public void queryUserItemByNameTest() {
        //user_1 have the item_1
        int greaterThanZero = getInstance().queryUserItemByName(usr_1, "potion").size();
        Assert.assertTrue("List size must be greater than 0", greaterThanZero > 0);

        //user_2 don't have the item_4
        int zero = getInstance().queryUserItemByName(usr_2, "awakening").size();
        Assert.assertTrue("List size must be 0", zero == 0);
    }

    @Test
    public void deleteUserItems() {
        //Deleting all items from usr_4
        getInstance().deleteUserItems(usr_4);
        Assert.assertTrue(getInstance().getUsersMap().get(4).getItemList().isEmpty());
    }

    @Test
    public void userToUserItemTransferTest() {
        //Transfering item_4 from user_1 to user_2...
        Item origin = getInstance().getUsersMap().get(1).getItem(4);
        getInstance().userToUserItemTransfer(usr_1,usr_2,origin);
        //Cheking if user_2 has got item_4
        Assert.assertEquals(getInstance().getUsersMap().get(2).getItem(4).getId(),origin.getId());
        //Checking if user_1 has deleted his item_4
        Assert.assertNull(getInstance().getUsersMap().get(1).getItem(4));
    }

    @Test
    public void loadMapTest() {
        Assert.assertTrue(WorldGameScreen.getInstance().loadMap("map1.txt"));
    }
*/

}

