package presentation.boundary;

import utility.Packet;

/**
 * Created by antonio on 27/10/15.
 */
public class SchermataDettagliPrenotazione  extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "Prenotazione";
    private static final String REQUEST = "dettagli";

    public SchermataDettagliPrenotazione(Packet packet) {
        super(packet, BOUNDARY_PATH, REQUEST);
    }
}