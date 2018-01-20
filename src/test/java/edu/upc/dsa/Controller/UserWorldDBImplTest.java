package edu.upc.dsa.Controller;

import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.ExceptionHandler.DAOUserException;
import edu.upc.dsa.ExceptionHandler.UserWorldDbException;
import edu.upc.dsa.Model.Main.Location;
import edu.upc.dsa.Model.Main.User;
import org.junit.*;
import org.junit.rules.ExpectedException;


import static edu.upc.dsa.Controller.API.UserWorldDBImpl.getInstance;

/**
 * Unit test for simple App.
 */
public class UserWorldDBImplTest {

    private User oriol;
    private User marc;


    @Before
    public void setUp() throws Exception {
        oriol = new User(2, "Oriol", "12345678", "oriol@lol.com", new Location(8, 2));
        marc = new User(0, "Marc", "lol", "crack.mike@gmail.com", new Location(8, 2));
    }


    @After
    public void tearDown() throws Exception {
        getInstance().deleteUser(oriol);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Test functions

    @Test
    public void registerTest() {
        //Creating new user
        try {
            Assert.assertEquals(getInstance().register(oriol).getId(), 2);
            Assert.assertEquals(getInstance().register(oriol).getId(), 2);
        } catch (UserWorldDbException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest()  {
        try {

            Assert.assertEquals(getInstance().login(marc).getId(), 0);
            Assert.assertEquals(getInstance().login(oriol).getId(), 2);

        } catch (UserWorldDbException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getItems() {

        try {
            //setUserItem()
            Assert.assertEquals(getInstance().getItems(0).size(), 1);
        } catch (DAOUserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getItem() {
        try {
            Assert.assertEquals(getInstance().getItem(0).getName(), "junit");
        }
        catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser() {
        try {
            getInstance().register(oriol);
            Assert.assertTrue(getInstance().deleteUser(oriol));
        }
        catch (DAOException e) {
            e.printStackTrace();
        }
    }


}
