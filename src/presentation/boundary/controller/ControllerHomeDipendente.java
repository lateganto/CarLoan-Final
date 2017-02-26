package presentation.boundary.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 19/10/15.
 */
public class ControllerHomeDipendente extends Controller {

    @FXML
    GridPane secondPane;

    private final FrontController fc = FCFactory.getFC();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(String request) {

    }
}
