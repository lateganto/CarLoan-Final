package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 27/10/15.
 */
public class SchermataNuovoContratto extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Contratto";
    private static final String REQUEST = "nuovo";

    public SchermataNuovoContratto(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}
