package edu.upc.dsa.Controller;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOUserItemException;
import edu.upc.dsa.ExceptionHandler.UserItemWorldDbException;
import edu.upc.dsa.Model.Relation.UserItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static edu.upc.dsa.Controller.API.UserItemWorldDBImpl.getInstance;

public class UserItemWorldDBImplTest {

    private UserItem joseanPotion;

    @Before
    public void setUp() throws Exception {
        joseanPotion = new UserItem(1, 1,1);

    }

    @After
    public void tearDown() throws Exception {
        getInstance().deleteUserItem(joseanPotion);
    }

    @Test
    public void setUserItem() {
        try {
            Assert.assertTrue(getInstance().setUserItem(joseanPotion));
            getInstance().setUserItem(joseanPotion);
        } catch (UserItemWorldDbException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserItem () {
        try {
            getInstance().setUserItem(joseanPotion);
            Assert.assertTrue(getInstance().deleteUserItem(joseanPotion));

        }
        catch (UserItemWorldDbException e) {
            e.printStackTrace();
        }
    }




}
