package presentation.boundary;

import utility.Packet;

/**
 * Created by antonio on 29/10/15.
 */
public class SchermataDettagliFattura  extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Fattura";
    private static final String REQUEST = "dettagli";

    public SchermataDettagliFattura(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}