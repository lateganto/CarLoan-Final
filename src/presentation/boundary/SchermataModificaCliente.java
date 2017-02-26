package presentation.boundary;

import utility.Packet;

/**
 * Created by salvatore on 26/10/15.
 */
public class SchermataModificaCliente extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Cliente";
    private static final String REQUEST = "modifica";

    public SchermataModificaCliente(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}
