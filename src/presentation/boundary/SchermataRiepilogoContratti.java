package presentation.boundary;


import utility.Packet;

/**
 * Created by salvatore on 25/10/15.
 */
public class SchermataRiepilogoContratti extends MainStage implements Boundary {
    private static final String CHILD_BOUNDARY_PATH = "Riepilogo";
    private static final String REQUEST = "contratto";


    public SchermataRiepilogoContratti(Packet packet) {
        super(packet, CHILD_BOUNDARY_PATH, REQUEST);
    }

    @Override
    public Object showWindow(Packet packet) {
        return null;
    }
}
