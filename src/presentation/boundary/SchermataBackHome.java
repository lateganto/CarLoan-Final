package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 19/10/15.
 */
public class SchermataBackHome extends MainStage implements Boundary {
    private static final String HOME_BOUNDARY_PATH = "Home";

    public SchermataBackHome(Packet packet) {
        //TODO da rivedere parametro request
        super(packet, HOME_BOUNDARY_PATH + packet.get("tipo"), packet.get("tipo").toString());

        setTitle("Home " + packet.get("tipo"));
        setResizable(true);
    }

    @Override
    public Object showWindow(Packet packet) {
        return null;
    }
}
