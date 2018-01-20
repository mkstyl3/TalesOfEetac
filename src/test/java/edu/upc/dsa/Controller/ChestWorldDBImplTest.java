package edu.upc.dsa.Controller;

import edu.upc.dsa.Controller.API.ChestWorldDBImpl;
import edu.upc.dsa.ExceptionHandler.ChestWorldDbException;
import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Main.Chest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static edu.upc.dsa.Controller.API.UserWorldDBImpl.getInstance;

public class ChestWorldDBImplTest {

    private Chest chest;

    @Before
    public void setUp() throws Exception {
      chest = new Chest(2,"Geant chest");
    }


    @After
    public void tearDown() throws Exception {
        ChestWorldDBImpl.getInstance().deleteChest(chest);
    }


    @Test
    public void insertChestTest() {
        try {
            Assert.assertTrue(ChestWorldDBImpl.getInstance().setChest(chest));
        } catch (ChestWorldDbException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteChestTest () {
        try {
            ChestWorldDBImpl.getInstance().setChest(chest);
            Assert.assertTrue(ChestWorldDBImpl.getInstance().deleteChest(chest));
        }
        catch (ChestWorldDbException e) {
            e.printStackTrace();
        }
    }
}
