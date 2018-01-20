package edu.upc.dsa.Controller;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOItemException;
import edu.upc.dsa.ExceptionHandler.ItemWorldDbException;
import edu.upc.dsa.Model.Main.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemWorldDBImplTest {

    Item junitItem;


    @Before
    public void setUp() throws Exception {
        //junitItem = new Item(1, "junitItem", );
    }
    /*
    @Test
    public void setItem() {
        try {
            Assert.assertTrue(DAOImpl.getInstance().insertItem(i));

        } catch (DAOItemException e) {
            throw new ItemWorldDbException(e);
        }
    }*/

}
