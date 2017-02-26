package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 22/10/15.
 */
public class SchermataNuovoCliente extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Cliente";
    private static final String REQUEST = "nuovo";

    public SchermataNuovoCliente(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}
