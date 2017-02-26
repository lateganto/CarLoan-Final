package presentation.boundary.controller;

import business.to.TOContratto;
import business.to.TOFactory;
import business.to.TransferObject;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;
import utility.Packet;
import utility.exception.CommonException;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerContratto extends Controller {

    private final String SWITCHABLE_CASE_NUOVO = "nuovo";
    private final String SWITCHABLE_CASE_DETTAGLI = "dettagli";
    private final String SWITCHABLE_CASE_MODIFICA = "modifica";

    private final FrontController fc = FCFactory.getFC();
    private TOContratto contratto = (TOContratto) TOFactory.getTO("TOContratto");

    @FXML
    private VBox mainPane;

    @FXML
    private Text titolo;

    @FXML
    private TextField idTextField;

    @FXML
    private ChoiceBox<String> chilometraggioChoiceBox;

    @FXML
    private ChoiceBox<String> clienteChoiceBox;

    @FXML
    private ChoiceBox<String> autoChoiceBox;

    @FXML
    private ChoiceBox<String> agenziaRestChoiceBox;

    @FXML
    private ChoiceBox<String> statoChoiceBox;

    @FXML
    private TextField kmPercorsiTextField;

    @FXML
    private TextField agenziaTextField;

    @FXML
    private SplitMenuButton optionalSplitMenuButton;

    @FXML
    private TextField kmPrevistiTextField;

    @FXML
    private TextField tarBaseTextField;

    @FXML
    private TextField tarFinaleTextField;

    @FXML
    private TextField minutiRitiroTextField;

    @FXML
    private DatePicker dataRestDatePicker;

    @FXML
    private DatePicker dataRitiroDatePicker;

    @FXML
    private TextField minutiRestTextField;

    @FXML
    private TextField oraRestTextField;

    @FXML
    private TextField oraRitiroTextField;

    @FXML
    private Button modificaButton;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Separator separator;

    @FXML
    private Button tarBaseButton;

    @FXML
    private Button tarFinaleButton;

    @FXML
    private Button esportaButton;

    @FXML
    private Button stampaButton;

    private boolean modifica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chilometraggioChoiceBox.setItems(FXCollections.observableArrayList("Limitato", "Illimitato"));

        try {
            initAgenziaChoiceBox();
            initClienteChoiceBox();
            initOptionalSplitMenu();
        } catch (CommonException e) {
            e.reportException();
        }
    }

    @Override
    public void start(String request) {

        try {
            switchMode(request);
        } catch (CommonException e) {
            e.reportException();
        }
    }

    private void initFields() {
        contratto = (TOContratto) runningData.get("transfer");

        idTextField.setText(contratto.getId());
        agenziaTextField.setText(contratto.getAgenzia());
        agenziaRestChoiceBox.setValue(contratto.getAgenziaRestituzione());
        clienteChoiceBox.setValue(contratto.getCliente());
        autoChoiceBox.setValue(contratto.getAuto());
        chilometraggioChoiceBox.setValue(contratto.getChilometraggio());
        kmPrevistiTextField.setText(String.valueOf(contratto.getChilometriPrevisti()));
        //TODO da aggiustare gli optional
        //optionalSplitMenuButton

        dataRitiroDatePicker.setValue(LocalDate.from(contratto.getDataInizio()));
        oraRitiroTextField.setText(String.valueOf(contratto.getDataInizio().getHour()));
        minutiRitiroTextField.setText(String.valueOf(contratto.getDataInizio().getMinute()));
        dataRestDatePicker.setValue(LocalDate.from(contratto.getDataFine()));
        oraRestTextField.setText(String.valueOf(contratto.getDataInizio().getHour()));
        minutiRestTextField.setText(String.valueOf(contratto.getDataInizio().getMinute()));

        //TODO if vedi cliente
        statoChoiceBox.setValue(contratto.getStato());
        kmPercorsiTextField.setText(String.valueOf(contratto.getChilometriPercorsi()));

        tarBaseTextField.setText(String.valueOf(contratto.getTariffaBase()));
        tarFinaleTextField.setText(String.valueOf(contratto.getTariffaFinale()));
    }
















    private void switchMode(String request) throws CommonException{
        String libera = "L";
        String occupata = "O";

        switch (request) {
            case SWITCHABLE_CASE_NUOVO:
                initAutoChoiceBox(libera);

                titolo.setText("Nuovo Contratto");
                idTextField.setDisable(true);
                agenziaTextField.setDisable(true);

                dataRitiroDatePicker.setValue(LocalDate.now());
                oraRitiroTextField.setText(String.valueOf(LocalDateTime.now().getHour()));
                minutiRitiroTextField.setText(String.valueOf(LocalDateTime.now().getMinute()));

                dataRitiroDatePicker.setEditable(false);
                oraRitiroTextField.setEditable(false);
                minutiRitiroTextField.setEditable(false);

                agenziaTextField.setText((String) runningData.get("agenzia"));

                statoChoiceBox.setDisable(true);

                kmPercorsiTextField.setDisable(true);
                tarFinaleTextField.setDisable(true);
                tarBaseTextField.setEditable(false);
                tarFinaleTextField.setEditable(false);
                tarFinaleButton.setDisable(true);

                esportaButton.setVisible(false);
                stampaButton.setVisible(false);
                modificaButton.setVisible(false);
                separator.setVisible(false);
                break;

            case SWITCHABLE_CASE_MODIFICA:
                initAutoChoiceBox(libera);

                modifica = true;
                titolo.setText("Modifica Contratto");

                idTextField.setDisable(true);
                agenziaTextField.setDisable(true);
                clienteChoiceBox.setDisable(true);
                autoChoiceBox.setDisable(true);
                chilometraggioChoiceBox.setDisable(true);
                kmPrevistiTextField.setDisable(true);
                optionalSplitMenuButton.setDisable(true);
                dataRitiroDatePicker.setDisable(true);
                oraRitiroTextField.setDisable(true);
                minutiRitiroTextField.setDisable(true);
                tarBaseTextField.setEditable(false);
                tarBaseButton.setDisable(true);

                esportaButton.setVisible(false);
                stampaButton.setVisible(false);
                modificaButton.setVisible(false);
                separator.setVisible(false);

                initFields();
                break;

            case SWITCHABLE_CASE_DETTAGLI:
                initAutoChoiceBox(occupata);

                titolo.setText("Dettagli Contratto");

                idTextField.setEditable(false);
                agenziaTextField.setEditable(false);
                agenziaRestChoiceBox.setDisable(true);
                clienteChoiceBox.setDisable(true);
                autoChoiceBox.setDisable(true);
                chilometraggioChoiceBox.setDisable(true);
                kmPrevistiTextField.setEditable(false);
                dataRitiroDatePicker.setEditable(false);
                oraRitiroTextField.setEditable(false);
                minutiRitiroTextField.setEditable(false);
                dataRestDatePicker.setEditable(false);
                oraRestTextField.setEditable(false);
                minutiRestTextField.setEditable(false);
                statoChoiceBox.setDisable(true);
                kmPercorsiTextField.setEditable(false);
                tarBaseTextField.setEditable(false);
                tarFinaleTextField.setEditable(false);
                tarBaseButton.setDisable(true);
                tarFinaleButton.setDisable(true);

                confermaButton.setDisable(true);
                annullaButton.setText("Chiudi");
                initFields();

                break;
        }
    }


    private void initAgenziaChoiceBox() throws CommonException {

        List<TransferObject> toList = (List<TransferObject>) fc.processRequest("readAllAgenzie", null);
        List<String> choiceList = new LinkedList<>();

        for (TransferObject to : toList) {
            choiceList.add(to.getId() + " - " + to.getAttributes().get(0));
        }

        agenziaRestChoiceBox.setItems(FXCollections.observableArrayList(choiceList));

    }

    private void initClienteChoiceBox() throws CommonException {
        List<String> choiceList = new LinkedList<>();
        List<TransferObject> toList = (List<TransferObject>) fc.processRequest("readAllClienti", null);

        for (TransferObject to : toList) {
            choiceList.add(to.getId() + " - " + to.getAttributes().get(0) + " " + to.getAttributes().get(1));
        }

        clienteChoiceBox.setItems(FXCollections.observableArrayList(choiceList));
    }

    private void initAutoChoiceBox(String status) throws CommonException {
        List<String> choiceList = new LinkedList<>();
        Packet packet = new Packet();
        packet.set("criterion", "agenzia");
        packet.set("agenzia", runningData.get("agenzia"));
        packet.set("stato", status);
        //TODO vedere che a nuovo mostro solo le auto libere
        List<TransferObject> toList = (List<TransferObject>) fc.processRequest("readAllAutoAgenzia", packet);

        for (TransferObject to : toList) {
            choiceList.add(to.getId() + " - " + to.getAttributes().get(0));
        }

        autoChoiceBox.setItems(FXCollections.observableArrayList(choiceList));
    }

    private void initOptionalSplitMenu() throws CommonException {

        Packet packet = new Packet();
        packet.set("criterion", "stato");
        packet.set("stato", "A");
        List<TransferObject> toList = (List<TransferObject>) fc.processRequest("readAllOptional", packet);

        for (TransferObject to : toList) {
            CheckMenuItem checkMenuItem = new CheckMenuItem();
            checkMenuItem.setText(to.getId() + " - " + to.getAttributes().get(0));
            optionalSplitMenuButton.getItems().add(checkMenuItem);
        }
    }
}
