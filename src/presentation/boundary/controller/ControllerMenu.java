package presentation.boundary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;
import utility.exception.CommonException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 19/10/15.
 */
public class ControllerMenu extends Controller {

    @FXML
    VBox mainPane;

    @FXML
    Label topTitle;

    @FXML
    Button backHomeButton;

    @FXML
    MenuItem fileProfilo;

    @FXML
    MenuItem fileAnagrafica;

    @FXML
    MenuItem fileInfoDitta;

    @FXML
    MenuItem fileInfoAgenzia;

    @FXML
    Menu agenzie;

    @FXML
    Menu dipendenti;

    @FXML
    Menu contratti;

    @FXML
    Menu prenotazioni;

    @FXML
    Menu auto;

    @FXML
    MenuItem autoNuovo;

    @FXML
    Menu clienti;

    @FXML
    MenuItem clientiNuovo;

    @FXML
    Menu fatture;

    @FXML
    MenuItem fattureNuovo;

    @FXML
    Menu scadenze;

    @FXML
    Menu optional;

    @FXML
    MenuItem optionalNuovo;

    @FXML
    Menu listino;


    private final FrontController fc = FCFactory.getFC();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(String request) {
        switch (request) {
            case "AdminSistema":
                fileInfoAgenzia.setVisible(false);
                dipendenti.setVisible(false);
                contratti.setVisible(false);
                prenotazioni.setVisible(false);
                autoNuovo.setVisible(false);
                clientiNuovo.setVisible(false);
                fattureNuovo.setVisible(false);
                scadenze.setVisible(false);
                topTitle.setText("Home Admin Sistema");
                break;

            case "AdminAgenzia":
                fileInfoDitta.setVisible(false);
                //TODO da rivedere
                fileAnagrafica.setVisible(false);
                agenzie.setVisible(false);
                contratti.setVisible(false);
                prenotazioni.setVisible(false);
                clientiNuovo.setVisible(false);
                optionalNuovo.setVisible(false);
                topTitle.setText("Home Admin Agenzia");
                break;

            case "Dipendente":
                fileInfoAgenzia.setVisible(false);
                fileInfoDitta.setVisible(false);
                fileAnagrafica.setVisible(false);
                agenzie.setVisible(false);
                dipendenti.setVisible(false);
                autoNuovo.setVisible(false);
                optional.setVisible(false);
                topTitle.setText("Home Dipendente");
                break;
        }

    }

    public void onEsci(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void goBackHome(ActionEvent event) {
        try {
            fc.processRequest("showBackToHome", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Home Dipendente");
        backHomeButton.setVisible(false);
    }

    public void onModificaProfilo(ActionEvent event) {
        try {
            fc.processRequest("showModificaProfilo", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
    }

    public void onRiepilogoContratti(ActionEvent event) {
        try {
            fc.processRequest("showRiepilogoContratti", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Riepilogo Contratti");
        backHomeButton.setVisible(true);
    }

    public void onRiepilogoPrenotazioni(ActionEvent event) {
        try {
            fc.processRequest("showRiepilogoPrenotazioni", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Riepilogo Prenotazioni");
        backHomeButton.setVisible(true);
    }

    public void onRiepilogoAuto(ActionEvent event) {
        try {
            fc.processRequest("showRiepilogoAuto", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Riepilogo Auto");
        backHomeButton.setVisible(true);
    }

    public void onRiepilogoClienti(ActionEvent event) {
        try {
            fc.processRequest("showRiepilogoClienti", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Riepilogo Clienti");
        backHomeButton.setVisible(true);
    }

    public void onRiepilogoFatture(ActionEvent event) {
        try {
            fc.processRequest("showRiepilogoFatture", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
        topTitle.setText("Riepilogo Fatture");
        backHomeButton.setVisible(true);
    }










    //TODO vedere il pacchetto da passargli
    public void onNuovoContratto(ActionEvent event) {
        //Packet packet = new Packet();
        //packet.set("agenzia", runningData.get("agenzia"));
        try {
            fc.processRequest("showNuovoContratto", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
    }

    public void onNuovoPrenotazione(ActionEvent event) {
        try {
            fc.processRequest("showNuovoPrenotazione", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
    }

    public void onNuovoCliente (ActionEvent event) {
        try {
            fc.processRequest("showNuovoCliente", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
    }

    public void onNuovoFattura(ActionEvent event) {
        try {
            fc.processRequest("showNuovoFattura", runningData);
        } catch (CommonException e) {
            e.reportException();
        }
    }

}
