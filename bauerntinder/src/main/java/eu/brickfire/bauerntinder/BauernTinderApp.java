package eu.brickfire.bauerntinder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import eu.brickfire.bauerntinder.module.BauernTinderModule;
import eu.brickfire.bauerntinder.rest.FieldEndPoint;
import eu.brickfire.bauerntinder.rest.PersonEndPoint;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class BauernTinderApp {


    static Injector injector;
    private static String CLASS = "jersey.config.server.provider.classnames";

    public static void main(String[] args) throws Exception {
        injector = Guice.createInjector(new BauernTinderModule());
        BauernTinderApp instance = injector.getInstance(BauernTinderApp.class);
        instance.start();
    }

    private void start() throws Exception {

        Server server = new Server(Integer.parseInt(System.getenv("BAUERNTINDER_PORT")));

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        servletContextHandler.addServlet(ServletContainer.class, "/");
        servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        servletContextHandler.addFilter(BauernTinderFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));


        Map<String, Class> paths = new HashMap<>();
        paths.put(PersonEndPoint.PATH, PersonEndPoint.class);
        paths.put(FieldEndPoint.PATH, FieldEndPoint.class);

        paths.forEach((path, point) -> {
            ServletHolder serHol = servletContextHandler.addServlet(ServletContainer.class, "/rest/json/" + path + "*");
            serHol.setInitOrder(1);
            serHol.setInitParameter(CLASS, point.getCanonicalName());
        });

        server.start();
        server.join();

    }

    public static Injector getInjector() {
        return injector;
    }
}
