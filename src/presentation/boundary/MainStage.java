package presentation.boundary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentation.boundary.controller.Controller;
import utility.Packet;

import java.io.IOException;

/**
 * Created by salvatore on 19/10/15.
 */
public class MainStage extends Stage {

    private static final String FXML_FOLDER = "/presentation/boundary/fxml/";
    private static final String FXML_EXTENSION = ".fxml";

    //TODO da controllare!!!
    protected static VBox root;
    protected static Scene scene;

    public MainStage(Packet packet, String rootSchemeResource, String childSchemeResource,
                     String title, String request) {

        rootSchemeResource = FXML_FOLDER + rootSchemeResource + FXML_EXTENSION;
        childSchemeResource = FXML_FOLDER + childSchemeResource + FXML_EXTENSION;
        FXMLLoader rootFxmlLoader = new FXMLLoader(getClass().getResource(rootSchemeResource));
        FXMLLoader childFxmlLoader = new FXMLLoader(getClass().getResource(childSchemeResource));

        try {
            root = rootFxmlLoader.load();
        } catch (IOException e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        try {
            root.getChildren().add(2, childFxmlLoader.load());
        } catch (IOException e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        Controller rootController = rootFxmlLoader.getController();
        Controller childController = childFxmlLoader.getController();

        rootController.initData(packet);
        childController.initData(packet);

        rootController.start(request);
        childController.start(null);

        scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        setScene(scene);
        setTitle(title);

    }

    public MainStage(Packet packet, String childSchemeResource, String request) {
        childSchemeResource = FXML_FOLDER + childSchemeResource + FXML_EXTENSION;
        FXMLLoader childFxmlLoader = new FXMLLoader(getClass().getResource(childSchemeResource));

        Pane child = null;
        try {
            child = childFxmlLoader.load();
        } catch (IOException e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        Controller childController = childFxmlLoader.getController();
        childController.initData(packet);
        childController.start(request);

        root = (VBox) scene.getRoot();
        root.getChildren().remove(2);
        //TODO controll null!
        root.getChildren().add(2, child);

    }
}
