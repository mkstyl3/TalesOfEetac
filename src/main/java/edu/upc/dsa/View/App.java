package edu.upc.dsa.View;

import edu.upc.dsa.Controller.API.UserWorldImpl;
import edu.upc.dsa.Controller.MoveToAndroidThings.GameScreenImpl;
import edu.upc.dsa.Model.Main.Location;
import edu.upc.dsa.Model.Main.Map;
import edu.upc.dsa.Model.Main.User;
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
        // set a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.View","edu.upc.dsa.Controller");
        //rc.register(MultiPartFeature.class);
        // set and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        /*

        System.out.println(WorldUser.getInstance().set(usr_1));
        System.out.println(WorldUser.getInstance().set(usr_2));
        System.out.println(WorldUser.getInstance().set(usr_3));
        System.out.println(WorldUser.getInstance().set(usr_4));

        WorldUser.getInstance().del(4);

        WorldUser.getInstance().setItem(usr_1, item_1);
        WorldUser.getInstance().delItems(usr_3);
        WorldUser.getInstance().getItemByName(usr_4, "healing");
        WorldUser.getInstance().getItemByName(usr_1,"potion");
        */

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./web/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));



        GameScreenImpl.getInstance().objectInitializations();
        User u = new User(1, "Marc", "1234",new Location(5,4));
        UserWorldImpl.getInstance().set(u);
        Map map = GameScreenImpl.getInstance().createMap(GameScreenImpl.getInstance().getCurrentMapId());
        GameScreenImpl.getInstance().setMap(map);
        GameScreenImpl.getInstance().initialUserLocation(u);

        //Exam procedures

        //UserWorld.getInstance().initializeUsers();

        Scanner scanner = new Scanner(System.in);
        char input;
        Boolean bucle = true;
        while(bucle) {
            printScreen(GameScreenImpl.getInstance().getCurrentMapId());
            switch (input = scanner.nextLine().charAt(0)) {
                case 'a':
                    GameScreenImpl.getInstance().moveUserTo(u, "a");
                    break;
                case 'd':
                    GameScreenImpl.getInstance() .moveUserTo(u,"d");
                    break;
                case 'w':
                    GameScreenImpl.getInstance().moveUserTo(u,"w");
                    break;
                case 's':
                    GameScreenImpl.getInstance().moveUserTo(u,"s");
                    break;
            }
        }
    }


    private static void printScreen(int mapId) {
        Map map = GameScreenImpl.getInstance().getMap(mapId);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) System.out.println(map.getCellByCoords(i,j).getSYMBOL());
                else System.out.print(map.getCellByCoords(i,j).getSYMBOL());
            }
        }
    }



}
