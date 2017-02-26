package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 27/10/15.
 */
public class SchermataModificaContratto extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Contratto";
    private static final String REQUEST = "modifica";

    public SchermataModificaContratto(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}
