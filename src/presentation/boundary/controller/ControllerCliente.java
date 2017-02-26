package presentation.boundary.controller;

import business.to.TOCliente;
import business.to.TOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.boundary.OverlayStage;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;
import utility.Packet;
import utility.dialog.Dialog;
import utility.exception.CommonException;
import utility.exception.EmptyFieldsException;
import utility.exception.InvalidFormatException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 22/10/15.
 */
public class ControllerCliente extends Controller {

    private final String SWITCHABLE_CASE_NUOVO = "nuovo";
    private final String SWITCHABLE_CASE_DETTAGLI = "dettagli";
    private final String SWITCHABLE_CASE_MODIFICA = "modifica";

    private final FrontController fc = FCFactory.getFC();
    private TOCliente cliente = (TOCliente) TOFactory.getTO("TOCliente");

    @FXML
    private VBox mainPane;

    @FXML
    private Text titolo;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cognomeTextField;

    @FXML
    private TextField nrDocumentoTextField;

    @FXML
    private TextField cittaTextField;

    @FXML
    private TextField indirizzoTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nrPatenteTextField;

    @FXML
    private DatePicker dataRilascioDatePicker;

    @FXML
    private TextField codiceFiscaleTextField;

    @FXML
    private TextField nrCartaTextField;

    @FXML
    private TextField intCartaTextField;

    @FXML
    private ChoiceBox<String> tipoDocumentoChoiceBox;

    @FXML
    private TextField idTextField;

    @FXML
    private Button modificaButton;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Separator separator;

    private boolean modifica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoDocumentoChoiceBox.setItems(FXCollections.observableArrayList("Carta d'Identità", "Passaporto"));
    }

    @Override
    public void start(String request) {
        switch (request) {
            case SWITCHABLE_CASE_NUOVO:
                titolo.setText("Nuovo Cliente");
                idTextField.setDisable(true);
                modificaButton.setVisible(false);
                separator.setVisible(false);
                break;

            case SWITCHABLE_CASE_MODIFICA:
                modifica = true;
                titolo.setText("Modifica Cliente");
                modificaButton.setVisible(false);
                separator.setVisible(false);
                initFields();
                break;

            case SWITCHABLE_CASE_DETTAGLI:
                titolo.setText("Dettagli Cliente");
                confermaButton.setDisable(true);
                annullaButton.setText("Chiudi");
                nomeTextField.setEditable(false);
                cognomeTextField.setEditable(false);
                nrDocumentoTextField.setEditable(false);
                cittaTextField.setEditable(false);
                indirizzoTextField.setEditable(false);
                telefonoTextField.setEditable(false);
                emailTextField.setEditable(false);
                nrPatenteTextField.setEditable(false);
                dataRilascioDatePicker.setEditable(false);
                codiceFiscaleTextField.setEditable(false);
                nrCartaTextField.setEditable(false);
                intCartaTextField.setEditable(false);

                initFields();


                //tipoDocumentoChoiceBox;
                break;
        }
    }

    private void initFields() {
        cliente = (TOCliente) runningData.get("transfer");

        idTextField.setText(cliente.getId());
        nomeTextField.setText(cliente.getNome());
        cognomeTextField.setText(cliente.getCognome());
        nrDocumentoTextField.setText(cliente.getNumeroDocumento());
        cittaTextField.setText(cliente.getCitta());
        indirizzoTextField.setText(cliente.getIndirizzo());
        telefonoTextField.setText(cliente.getTelefono());
        emailTextField.setText(cliente.getEmail());
        nrPatenteTextField.setText(cliente.getNumeroPatente());
        dataRilascioDatePicker.setValue(cliente.getDataRilascioPatente());
        codiceFiscaleTextField.setText(cliente.getCodiceFiscale());
        nrCartaTextField.setText(cliente.getCartaCredito());
        intCartaTextField.setText(cliente.getIntestatarioCarta());
        String tipoDocumento = cliente.getTipoDocumento();
        if (tipoDocumento.equals("P")) {
            tipoDocumentoChoiceBox.setValue("Passaporto");
        }
    }

    public void onModify(ActionEvent event) {
        modifica = true;
        titolo.setText("Modifica Cliente");
        modificaButton.setVisible(false);
        separator.setVisible(false);

        confermaButton.setDisable(false);
        annullaButton.setText("Annulla");
        nomeTextField.setEditable(true);
        cognomeTextField.setEditable(true);
        nrDocumentoTextField.setEditable(true);
        cittaTextField.setEditable(true);
        indirizzoTextField.setEditable(true);
        telefonoTextField.setEditable(true);
        emailTextField.setEditable(true);
        nrPatenteTextField.setEditable(true);
        dataRilascioDatePicker.setEditable(true);
        codiceFiscaleTextField.setEditable(true);
        nrCartaTextField.setEditable(true);
        intCartaTextField.setEditable(true);
    }

    public void onConferma(ActionEvent event) {
        Packet packet = new Packet();

        try {
            checkEmptyFields();
            checkNumberFields();

            packet.set("id", idTextField.getText());
            packet.set("nome", nomeTextField.getText());
            packet.set("cognome", cognomeTextField.getText());
            packet.set("email", emailTextField.getText());
            packet.set("telefono", telefonoTextField.getText());
            packet.set("citta", cittaTextField.getText());
            packet.set("indirizzo", indirizzoTextField.getText());
            packet.set("cf", codiceFiscaleTextField.getText());
            packet.set("nrDocumento", nrDocumentoTextField.getText());
            //TODO da controllare
            packet.set("tipoDocumento", Character.toString(tipoDocumentoChoiceBox.getValue().charAt(0)));
            packet.set("nrPatente", nrPatenteTextField.getText());
            packet.set("nrCarta", nrCartaTextField.getText());
            packet.set("intCarta", intCartaTextField.getText());
            packet.set("dataRilascio", dataRilascioDatePicker.getValue());


            if (modifica) {
                fc.processRequest("updateCliente", packet);
                Dialog.showInformationDialog("Cliente aggiornato correttamente");
            } else {
                fc.processRequest("insertCliente", packet);
                Dialog.showInformationDialog("Cliente inserito correttamente");
            }

            getStage().close();

        } catch (EmptyFieldsException | InvalidFormatException e) {
            Dialog.showErrorDialog(e.getMessage());
        } catch (CommonException e) {
            e.reportException();
        }
    }

    private void checkEmptyFields() throws EmptyFieldsException {

        boolean notPassed;

        notPassed = ((nomeTextField.getText().isEmpty()) || (cognomeTextField.getText().isEmpty()) ||
                (nrDocumentoTextField.getText().isEmpty()) || (cittaTextField.getText().isEmpty()) ||
                (indirizzoTextField.getText().isEmpty()) || (telefonoTextField.getText().isEmpty()) ||
                (emailTextField.getText().isEmpty()) || (nrPatenteTextField.getText().isEmpty()) ||
                (dataRilascioDatePicker.getValue() == null) || (codiceFiscaleTextField.getText().isEmpty()) ||
                (nrCartaTextField.getText().isEmpty()) || (intCartaTextField.getText().isEmpty()) ||
                (tipoDocumentoChoiceBox.getValue() == null));

        if (notPassed) {
            throw new EmptyFieldsException("Completa prima tutti i campi!");
        }
    }

    private void checkNumberFields() throws InvalidFormatException {
        final String REGEX_NUMBER = "[0-9]*";

        if (!telefonoTextField.getText().matches(REGEX_NUMBER)) {
            throw new InvalidFormatException("Formato recapito Telefonico Errato!");
        }

        if (!nrCartaTextField.getText().matches(REGEX_NUMBER)) {
            throw new InvalidFormatException("Formato numero Carta Errato!");
        }
    }


    private OverlayStage getStage() {
        return (OverlayStage) mainPane.getScene().getWindow();
    }

    public void onAnnulla(ActionEvent event) {
        getStage().close();
    }
}
