package presentation.boundary.controller;

import business.to.TOFactory;
import business.to.TOPrenotazione;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by antonio on 27/10/15.
 */
public class ControllerPrenotazione extends Controller {

    private final String SWITCHABLE_CASE_NUOVO = "nuovo";
    private final String SWITCHABLE_CASE_DETTAGLI = "dettagli";

    private final FrontController fc = FCFactory.getFC();
    private TOPrenotazione prenotazione = (TOPrenotazione) TOFactory.getTO("TOPrenotazione");

    @FXML
    private VBox mainPane;

    @FXML
    private Text titolo;

    @FXML
    private TextField idTextField;

    @FXML
    private ChoiceBox clienteChoiceBox;

    @FXML
    private ChoiceBox autoChoiceBox;

    @FXML
    private TextField agenziaTextField;

    @FXML
    private ChoiceBox<String> tipoPrenotazioneChoiceBox;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private DatePicker scadenzaDatePicker;

    @FXML
    private TextField oreTextField;

    @FXML
    private TextField minutiTextField;

    @FXML
    private TextField accontoTextField;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoPrenotazioneChoiceBox.setItems(FXCollections.observableArrayList("Prepagata", "Non prepagata"));

        //agenziaTextField.setText("1");
        //idTextField.setText("1");
        //clienteChoiceBox.setItems(FXCollections.observableArrayList("899", "555"));
        //autoChoiceBox.setItems(FXCollections.observableArrayList("45", "89"));
    }

    @Override
    public void start(String request) {
        switch (request) {
            case SWITCHABLE_CASE_NUOVO:
                titolo.setText("Nuova Prenotazione");
                idTextField.setDisable(true);
                agenziaTextField.setDisable(true);
                break;
            case SWITCHABLE_CASE_DETTAGLI:
                titolo.setText("Dettagli Prenotazione");
                confermaButton.setDisable(true);
                annullaButton.setText("Chiudi");
                accontoTextField.setDisable(true);
                clienteChoiceBox.setDisable(true);
                scadenzaDatePicker.setDisable(true);
                idTextField.setDisable(true);
                dataDatePicker.setDisable(true);
                minutiTextField.setDisable(true);
                oreTextField.setDisable(true);
                tipoPrenotazioneChoiceBox.setDisable(true);
                autoChoiceBox.setDisable(true);
                agenziaTextField.setDisable(true);
                confermaButton.setDisable(true);
                initFields();
                break;
        }
    }

    private void initFields() {
        prenotazione = (TOPrenotazione) runningData.get("transfer");

        idTextField.setText(prenotazione.getId());
        clienteChoiceBox.setValue(prenotazione.getCliente());
        autoChoiceBox.setValue(prenotazione.getAuto());
        agenziaTextField.setText(prenotazione.getAgenzia());
        String tipoPrenotazione = prenotazione.getTipo();
        if(tipoPrenotazione.equals("P")) {
            tipoPrenotazioneChoiceBox.setValue("Prepagata");
        }
        else {
            tipoPrenotazioneChoiceBox.setValue("Non prepagata");
        }
        dataDatePicker.setValue(prenotazione.getDataPrenotazione());
        scadenzaDatePicker.setValue(LocalDate.from(prenotazione.getScadenza()));
        oreTextField.setText(String.valueOf(prenotazione.getScadenza().getHour()));
        minutiTextField.setText(String.valueOf(prenotazione.getScadenza().getHour()));
        accontoTextField.setText(String.valueOf(prenotazione.getAcconto()));
    }

    public void onConferma(ActionEvent event) {
        Packet packet = new Packet();

        try {
            checkEmptyFields();
            checkNumberFields();
            checkExpireDateFields();

            packet.set("id", idTextField.getText());
            packet.set("cliente", clienteChoiceBox.getValue());
            packet.set("auto", autoChoiceBox.getValue());
            packet.set("agenzia", agenziaTextField.getText());
            if(tipoPrenotazioneChoiceBox.getValue().equals("Prepagata")) {
                packet.set("tipo", "P");
            }
            else {
                packet.set("tipo", "NP");
            }
            packet.set("data", dataDatePicker.getValue());
            packet.set("scadenza", scadenzaDatePicker.getValue());
            packet.set("ore", oreTextField.getText());
            packet.set("minuti", minutiTextField.getText());
            packet.set("acconto", accontoTextField.getText());

            fc.processRequest("insertPrenotazione", packet);
            Dialog.showInformationDialog("Prenotazione inserita correttamente");


            getStage().close();

        } catch(EmptyFieldsException | InvalidFormatException e) {
            Dialog.showErrorDialog(e.getMessage());
        } catch (CommonException e) {
            e.reportException();
        }
    }

    private void checkEmptyFields() throws EmptyFieldsException {
        boolean notPassed;

        notPassed = ( (clienteChoiceBox.getValue()==null) || (autoChoiceBox.getValue()==null) ||
                (agenziaTextField.getText().isEmpty()) || (tipoPrenotazioneChoiceBox.getValue()==null) ||
                (dataDatePicker.getValue()==null) || (scadenzaDatePicker.getValue()==null) ||
                (oreTextField.getText().isEmpty()) || (minutiTextField.getText().isEmpty()) ||
                (accontoTextField.getText().isEmpty()) );

        if (notPassed) {
            throw new EmptyFieldsException("Completa prima tutti i campi!");
        }
    }

    private void checkNumberFields() throws InvalidFormatException {
        final String REGEX_NUMBER = "^[0-9]{10},[0-9]{2}$";

        if(!(String.valueOf(accontoTextField.getText())).matches(REGEX_NUMBER)) {
            throw new InvalidFormatException("Formato importo acconto errato!");
        }
    }

    private void checkExpireDateFields() throws InvalidFormatException {
        final String REGEX_HOUR = "^(([0-1]?[0-9])|(2[0-4]))$";
        final String REGEX_MINUTE = "^([0-6]?[0-9])$";

        if(!oreTextField.getText().matches(REGEX_HOUR)) {
            throw new InvalidFormatException("Formato Ore Errato!");
        }

        if (!minutiTextField.getText().matches(REGEX_MINUTE)) {
            throw new InvalidFormatException("Formato Minuti Errato!");
        }
    }

    private OverlayStage getStage() {
        return (OverlayStage) mainPane.getScene().getWindow();
    }

    public void onAnnulla(ActionEvent event) {
        getStage().close();
    }

}