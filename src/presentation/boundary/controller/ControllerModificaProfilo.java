package presentation.boundary.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 20/10/15.
 */
public class ControllerModificaProfilo extends Controller {

    @FXML
    VBox mainPane;

    @FXML
    TextField usernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameField.setEditable(false);
    }

    @Override
    public void start(String request) {
        usernameField.setText((String) runningData.get("username"));
    }
}
