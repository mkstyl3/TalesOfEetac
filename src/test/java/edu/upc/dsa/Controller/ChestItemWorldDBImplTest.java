package edu.upc.dsa.Controller;

import edu.upc.dsa.Controller.API.ChestItemWorldDB;
import edu.upc.dsa.Controller.API.ChestItemWorldDBImpl;
import edu.upc.dsa.Controller.API.ChestWorldDBImpl;
import edu.upc.dsa.Controller.API.ItemWorldDBImpl;
import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.ChestItemWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOChestItemException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Main.Chest;
import edu.upc.dsa.Model.Main.Item;
import edu.upc.dsa.Model.Relation.ChestItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChestItemWorldDBImplTest {

    private ChestItem chestItem;
    private Chest chest;
    private Item item;

    public ChestItemWorldDBImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        item = new Item(0,"item", 0,"mayor healing", 50);
        chest = new Chest(2,"Geant chest");
        chestItem = new ChestItem(2,0);
    }


    @After
    public void tearDown() throws Exception {
        ChestWorldDBImpl.getInstance().deleteChest(chest);
        ItemWorldDBImpl.getInstance().deleteItem(item);
        ChestItemWorldDBImpl.getInstance().deleteChestItems(2);
    }

    @Test
    public void setChestItemTest() {
        try {
            Assert.assertTrue(ChestItemWorldDBImpl.getInstance().setChestItem(chestItem));

        } catch (ChestItemWorldDbException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteChestItemsTest() {
        try {
            ChestItemWorldDBImpl.getInstance().deleteChestItems(chestItem.getChestId());

        } catch (ChestItemWorldDbException e) {
            e.printStackTrace();
        }
    }
}
