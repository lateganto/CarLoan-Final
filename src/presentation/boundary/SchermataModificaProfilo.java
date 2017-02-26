package presentation.boundary;

import javafx.stage.Modality;
import utility.Packet;

/**
 * Created by salvatore on 20/10/15.
 */
public class SchermataModificaProfilo extends OverlayStage implements Boundary {
    private static final String BOUNDARY_PATH = "profilo/ModificaProfilo";

    public SchermataModificaProfilo(Packet packet) {
        super(packet, BOUNDARY_PATH, "");

        setTitle("Modifica Profilo");
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
