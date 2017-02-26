package presentation.boundary.controller;

import business.to.TOFactory;
import business.to.TOFattura;
import javafx.collections.FXCollections;
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

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by antonio on 28/10/15.
 */
public class ControllerFattura extends Controller {

    private final String SWITCHABLE_CASE_NUOVO = "nuovo";
    private final String SWITCHABLE_CASE_DETTAGLI = "dettagli";

    private final FrontController fc = FCFactory.getFC();
    private TOFattura fattura = (TOFattura) TOFactory.getTO("TOFattura");

    @FXML
    private VBox mainPane;

    @FXML
    private Text titolo;

    @FXML
    private TextField idTextField;

    @FXML
    private ChoiceBox clienteChoiceBox;

    @FXML
    private ChoiceBox contrattoChoiceBox;

    @FXML
    private TextField agenziaTextField;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private TextField importoTextField;

    @FXML
    private ChoiceBox statoChoiceBox;

    @FXML
    private TextField descrizioneTextField;

    @FXML
    private Button confermaButton;

    @FXML
    private Button annullaButton;

    @FXML
    private Separator buttonSeparator;

    @FXML
    private Button esportaButton;

    @FXML
    private Button stampaButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statoChoiceBox.setItems(FXCollections.observableArrayList("Pagata", "Non pagata"));
    }

    @Override
    public void start(String request) {
        switch (request) {
            case SWITCHABLE_CASE_NUOVO:
                titolo.setText("Nuova Fattura");
                idTextField.setDisable(true);
                agenziaTextField.setDisable(true);
                stampaButton.setVisible(false);
                esportaButton.setVisible(false);
                buttonSeparator.setVisible(false);
                break;
            case SWITCHABLE_CASE_DETTAGLI:
                titolo.setText("Dettagli Fattura");
                confermaButton.setDisable(true);
                annullaButton.setText("Chiudi");
                idTextField.setDisable(true);
                clienteChoiceBox.setDisable(true);
                contrattoChoiceBox.setDisable(true);
                agenziaTextField.setDisable(true);
                dataDatePicker.setDisable(true);
                importoTextField.setDisable(true);
                statoChoiceBox.setDisable(true);
                descrizioneTextField.setDisable(true);

                initFields();
                break;
        }
    }

    private void initFields() {
        fattura = (TOFattura) runningData.get("transfer");

        idTextField.setText(fattura.getId());
        clienteChoiceBox.setValue(fattura.getCliente());
        contrattoChoiceBox.setValue(fattura.getContratto());
        agenziaTextField.setText(fattura.getAgenzia());
        dataDatePicker.setValue(fattura.getDataEmissione());
        importoTextField.setText(String.valueOf(fattura.getImporto()));
        if(fattura.getStato().equals("P")) {
            statoChoiceBox.setValue("Pagato");
        }
        else {
            statoChoiceBox.setValue("Non pagato");
        }
        descrizioneTextField.setText(fattura.getDescrizione());
    }


    public void onConferma(ActionEvent event) {
        Packet packet = new Packet();

        try {
            checkEmptyFields();
            checkNumberFields();

            packet.set("id", idTextField.getText());
            packet.set("cliente", clienteChoiceBox.getValue());
            packet.set("contratto", contrattoChoiceBox.getValue());
            packet.set("agenzia", agenziaTextField.getText());
            packet.set("data", dataDatePicker.getValue());
            packet.set("importo", importoTextField.getText());
            packet.set("stato", statoChoiceBox.getValue());
            packet.set("descrizione", descrizioneTextField.getText());

            fc.processRequest("insertFattura", packet);
            utility.dialog.Dialog.showInformationDialog("Fattura inserita correttamente");

            getStage().close();

        } catch(EmptyFieldsException | InvalidFormatException e) {
            Dialog.showErrorDialog(e.getMessage());
        } catch (CommonException e) {
            e.reportException();
        }
    }

    private void checkEmptyFields() throws EmptyFieldsException {
        boolean notPassed;

        notPassed = ( (clienteChoiceBox.getValue()==null) || (contrattoChoiceBox.getValue()==null) ||
                (agenziaTextField.getText().isEmpty()) || (dataDatePicker.getValue()==null) ||
                (importoTextField.getText().isEmpty()) || (statoChoiceBox.getValue()==null) );

        if(notPassed) {
            throw new EmptyFieldsException("Completa prima tutti i campi!");
        }
    }

    private void checkNumberFields() throws InvalidFormatException {
        final String REGEX_NUMBER = "^[0-9]{10},[0-9]{2}$";

        if(!(String.valueOf(importoTextField.getText())).matches(REGEX_NUMBER)) {
            throw new InvalidFormatException("Formato importo fattura errato!");
        }
    }

    private OverlayStage getStage() {
        return (OverlayStage) mainPane.getScene().getWindow();
    }

    public void onAnnulla(javafx.event.ActionEvent event) {
        getStage().close();
    }

    //TODO onStampa e onEsporta
}
