package edu.upc.dsa.View;

import edu.upc.dsa.Controller.*;
import edu.upc.dsa.Model.Cell.Cell;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.Map;
import edu.upc.dsa.Model.User;

import java.util.*;

/**
 * Second version!
 *
 */
public class App {
    public static void main(String[] args) {
        /*

        User usr_1 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        User usr_2 = new User(2, "Gerard", "1234", 34, 30, 30, 30);
        User usr_3 = new User(3, "Ivan", "1234", 34, 30, 30, 30);
        User usr_4 = new User(4, "Mario", "1234", 34, 30, 30, 30);

        Item item_1 = new Item(1,"potion", 3, "minor heal", 0.2, 20);
        Item item_2 = new Item(2,"paralize", 3, "minor heal", 0.2, 20);
        Item item_3 = new Item(3,"mana", 3, "minor heal", 0.2, 20);
        Item item_4 = new Item(4,"speed", 3, "minor heal", 0.2, 20);

        System.out.println(WorldUser.getInstance().createUser(usr_1));
        System.out.println(WorldUser.getInstance().createUser(usr_2));
        System.out.println(WorldUser.getInstance().createUser(usr_3));
        System.out.println(WorldUser.getInstance().createUser(usr_4));

        WorldUser.getInstance().deleteUser(4);

        WorldUser.getInstance().addItemUser(usr_1, item_1);
        WorldUser.getInstance().deleteUserItems(usr_3);
        WorldUser.getInstance().queryUserItemByName(usr_4, "healing");
        WorldUser.getInstance().queryUserItemByName(usr_1,"potion");
        */


        WorldGameScreen.getInstance().objectInitializations();
        User u = new User(1, "Marc", "1234", 34, 30, 30, new Location(5,4));
        Map map = WorldGameScreen.getInstance().createMap(WorldGameScreen.getInstance().getCurrentMapId());
        WorldGameScreen.getInstance().loadMap(map);
        WorldGameScreen.getInstance().initialUserLocation(u);

        Scanner scanner = new Scanner(System.in);
        char input;
        Boolean bucle = true;
        while(bucle) {
            printScreen(WorldGameScreen.getInstance().getCurrentMapId());
            switch (input = scanner.nextLine().charAt(0)) {
                case 'a':
                    WorldGameScreen.getInstance().moveUserTo(u, "a");
                    break;
                case 'd':
                    WorldGameScreen.getInstance().moveUserTo(u,"d");
                    break;
                case 'w':
                    WorldGameScreen.getInstance().moveUserTo(u,"w");
                    break;
                case 's':
                    WorldGameScreen.getInstance().moveUserTo(u,"s");
                    break;
            }
        }
    }

    private static void printScreen(int mapId) {
        Map map = WorldGameScreen.getInstance().getMapsArray()[mapId - 1];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) System.out.println(map.getCellByCoords(i,j).getSymbol());
                else System.out.print(map.getCellByCoords(i,j).getSymbol());
            }
        }
    }

}
