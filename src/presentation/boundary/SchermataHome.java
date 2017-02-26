package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 19/10/15.
 */
public class SchermataHome extends MainStage implements Boundary {
    private static final String MENU_BOUNDARY_PATH = "Menu";
    private static final String HOME_BOUNDARY_PATH = "Home";

    public SchermataHome(Packet packet) {
        super(packet, MENU_BOUNDARY_PATH, HOME_BOUNDARY_PATH + packet.get("tipo"),
                "Sessione " + packet.get("username"), packet.get("tipo").toString());

        setResizable(true);
    }

    @Override
    public Object showWindow(Packet packet) {
        showAndWait();
        return null;
    }


}
