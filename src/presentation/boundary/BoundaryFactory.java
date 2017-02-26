package presentation.boundary;

import utility.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by salvatore on 11/10/15.
 */
public class BoundaryFactory {

    private static final String BOUNDARY_PATH = "presentation.boundary.";

    private BoundaryFactory() {

    }

    public static Boundary getBoundary(String request, Packet packet) {

        Boundary boundaryInstance = null;

        try {
            Class<?> boundaryClass = Class.forName(BOUNDARY_PATH + request);
            Constructor<?> constructor = boundaryClass.getConstructor(Packet.class);
            boundaryInstance = (Boundary) constructor.newInstance(packet);

        } catch (ClassNotFoundException | InvocationTargetException |
                NoSuchMethodException | InstantiationException | IllegalAccessException e) {

            //TODO gestire eccezioni
            e.printStackTrace();

        }

        return boundaryInstance;

    }
}
