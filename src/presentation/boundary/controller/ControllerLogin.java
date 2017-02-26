package presentation.boundary.controller;

import business.to.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;
import utility.Packet;
import utility.exception.CommonException;
import utility.security.Cryptographer;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLogin extends Controller {

    @FXML
    private GridPane mainPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private final FrontController fc = FCFactory.getFC();

    @FXML
    void onLogin(ActionEvent event) {

        errorLabel.setVisible(false);

        Packet loginData = new Packet();
        loginData.set("criterion", "login");
        loginData.set("username", usernameTextField.getText());
        loginData.set("password", Cryptographer.encrypt(passwordTextField.getText()));

        try {
            checkEmptyFields();
            TransferObject user = (TransferObject) fc.processRequest("login", loginData);

            if (user == null) {
                errorLabel.setText("Username o Password incoretti!");
                errorLabel.setVisible(true);
            } else {
                getStage().close();
                startSession((TOUtente) user);
            }

        } catch (CommonException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        }
    }

    private void checkEmptyFields() throws CommonException {

        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            throw new CommonException("Completa prima tutti i campi!");
        }
    }

    private void startSession(TOUtente utente) {

        Packet packet = new Packet();
        packet.set("id", utente.getId());
        packet.set("username", utente.getUsername());
        packet.set("tipo", utente.getTipo());

        final String adminSistema = "AdminSistema";
        final String adminAgenzia = "AdminAgenzia";
        final String dipendente = "Dipendente";

        try {
            switch (utente.getTipo()) {
                case adminSistema:
                    TOAdminSistema toAdminSistema = (TOAdminSistema) fc.processRequest("readAdminSistema", packet);
                    break;

                case adminAgenzia:
                    TOAdminAgenzia toAdminAgenzia = (TOAdminAgenzia) fc.processRequest("readAdminAgenzia", packet);
                    packet.set("agenzia", toAdminAgenzia.getAgenzia());
                    break;

                case dipendente:
                    TODipendente toDipendente = (TODipendente) fc.processRequest("readDipendente", packet);
                    packet.set("agenzia", toDipendente.getAgenzia());
                    break;

                //TODO da controllare
                default:
                    throw new CommonException();
            }

            fc.processRequest("showHome", packet);

        } catch (CommonException e) {
            e.reportException();
        }

    }

    private Stage getStage() {
        return (Stage) mainPane.getScene().getWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.setVisible(false);
    }

    @Override
    public void start(String request) {

    }

}
