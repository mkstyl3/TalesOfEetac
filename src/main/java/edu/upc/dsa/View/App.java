package edu.upc.dsa.View;

import edu.upc.dsa.Controller.*;

import java.util.*;

/**
 * First version!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*
        He visto más útil implementar el programa principal (que era para probar
        que las funciones funcionaban) con tests Junit. Mi intención es implementar el juego a partir de aquí.
        Si no lo ves correcto me lo dices Juan. Saludos!

        User usr_1 = new User(1, "Marc", "1234", 34, 30, 30, 30);
        User usr_2 = new User(2, "Gerard", "1234", 34, 30, 30, 30);
        User usr_3 = new User(3, "Ivan", "1234", 34, 30, 30, 30);
        User usr_4 = new User(4, "Mario", "1234", 34, 30, 30, 30);

        Item item_1 = new Item(1,"potion", 3, "minor heal", 0.2, 20);
        Item item_2 = new Item(2,"paralize", 3, "minor heal", 0.2, 20);
        Item item_3 = new Item(3,"mana", 3, "minor heal", 0.2, 20);
        Item item_4 = new Item(4,"speed", 3, "minor heal", 0.2, 20);

        System.out.println(World.getInstance().createUser(usr_1));
        System.out.println(World.getInstance().createUser(usr_2));
        System.out.println(World.getInstance().createUser(usr_3));
        System.out.println(World.getInstance().createUser(usr_4));

        World.getInstance().deleteUser(4);

        World.getInstance().addItemUser(usr_1, item_1);
        World.getInstance().deleteUserItems(usr_3);
        World.getInstance().queryUserItemByName(usr_4, "healing");
        World.getInstance().queryUserItemByName(usr_1,"potion");
        */

        World.getInstance().loadMap("map1.txt");
        ConsoleGameScreen.getInstance().locateUser(54);

        List<String> list = new ArrayList<>(World.getInstance().getHashOfMaps().values());
        int cont = 0;
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                if (j==9) System.out.println(list.get(cont));
                else System.out.print(list.get(cont));
                cont++;
            }
        }
    }
}
