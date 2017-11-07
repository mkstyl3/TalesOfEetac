package edu.upc.dsa.View;

import edu.upc.dsa.Controller.*;
import edu.upc.dsa.Model.Location;
import edu.upc.dsa.Model.Map;
import edu.upc.dsa.Model.User;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Second version!
 *
 */

/**
 * Main method.
 * @param
 * @throws IOException
 */
public class App {
    public static final String BASE_URI = "http://localhost:8080/talesofeetac/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.Controller");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        /*

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

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./web/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));



        GameScreenWorld.getInstance().objectInitializations();
        User u = new User(1, "Marc", "1234", 34, 30, 30, new Location(5,4));
        Map map = GameScreenWorld.getInstance().createMap(GameScreenWorld.getInstance().getCurrentMapId());
        GameScreenWorld.getInstance().loadMap(map);
        GameScreenWorld.getInstance().initialUserLocation(u);

        //Exam procedures

        UserWorld.getInstance().initializeUsers();

        Scanner scanner = new Scanner(System.in);
        char input;
        Boolean bucle = true;
        while(bucle) {
            printScreen(GameScreenWorld.getInstance().getCurrentMapId());
            switch (input = scanner.nextLine().charAt(0)) {
                case 'a':
                    GameScreenWorld.getInstance().moveUserTo(u, "a");
                    break;
                case 'd':
                    GameScreenWorld.getInstance() .moveUserTo(u,"d");
                    break;
                case 'w':
                    GameScreenWorld.getInstance().moveUserTo(u,"w");
                    break;
                case 's':
                    GameScreenWorld.getInstance().moveUserTo(u,"s");
                    break;
            }
        }
    }


    private static void printScreen(int mapId) {
        Map map = GameScreenWorld.getInstance().getMapsArray()[mapId - 1];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) System.out.println(map.getCellByCoords(i,j).getSymbol());
                else System.out.print(map.getCellByCoords(i,j).getSymbol());
            }
        }
    }



}
