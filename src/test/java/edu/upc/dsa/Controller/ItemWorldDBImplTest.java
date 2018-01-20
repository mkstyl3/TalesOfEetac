package edu.upc.dsa.Controller;


import edu.upc.dsa.Controller.API.ChestWorldDBImpl;
import edu.upc.dsa.Controller.API.ItemWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.ItemWorldDbException;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemWorldDBImplTest {

    private Item item;

    @Before
    public void setUp() throws Exception {
        item = new Item(0,"heal", 0,"mayor healing", 50);
    }


    @After
    public void tearDown() throws Exception {
        ItemWorldDBImpl.getInstance().deleteItem(item);
    }


    @Test
    public void insertItemTest() {
        try{
            Assert.assertTrue(ItemWorldDBImpl.getInstance().setItem(item));
        }
        catch (ItemWorldDbException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItemTest () {
        try {
            ItemWorldDBImpl.getInstance().setItem(item);
            Assert.assertTrue(ItemWorldDBImpl.getInstance().deleteItem(item));
        }
        catch (ItemWorldDbException e) {
            e.printStackTrace();
        }
    }

}
