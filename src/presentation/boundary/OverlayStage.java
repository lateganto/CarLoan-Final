package presentation.boundary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import presentation.boundary.controller.Controller;
import utility.Packet;

import java.io.IOException;

/**
 * Created by salvatore on 20/10/15.
 */
public class OverlayStage extends Stage implements Boundary {
    private static final String FXML_FOLDER = "/presentation/boundary/fxml/";
    private static final String FXML_EXTENSION = ".fxml";

    protected Region root;
    protected Scene scene;
    //protected double width;
    //protected double height;

    public OverlayStage(Packet packet, String schemeResource, String request) {

        Class<?> mainClass = getClass();
        schemeResource = FXML_FOLDER + schemeResource + FXML_EXTENSION;

        FXMLLoader fxmlLoader = new FXMLLoader(mainClass.getResource(schemeResource));

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        Controller controller = fxmlLoader.getController();
        controller.initData(packet);
        controller.start(request);

        //width = root.getPrefWidth();
        //height = root.getPrefHeight();

        scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        //this.setMinWidth(width);
        //this.setMinHeight(height);

        setScene(scene);
    }


    @Override
    public Object showWindow(Packet packet) {
        showAndWait();
        return null;
    }
}
