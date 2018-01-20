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
    public static final String BASE_URI = "http://10.192.111.244:8080/talesofeetac/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // set a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.View","edu.upc.dsa.Controller","edu.upc.dsa");
        //rc.register(MultiPartFeature.class);
        // set and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./web/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.out.println(String.format("Welcome to TalesOfEetac!"));

    }



}
