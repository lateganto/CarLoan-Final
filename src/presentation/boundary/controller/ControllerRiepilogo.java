package presentation.boundary.controller;

import business.to.TOCliente;
import business.to.TOFactory;
import business.to.TransferObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import presentation.boundary.modelClass.ModelClass;
import presentation.boundary.modelClass.ModelClassCliente;
import presentation.controller.FCFactory;
import presentation.controller.FrontController;
import utility.Packet;
import utility.exception.CommonException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 22/10/15.
 */
public class ControllerRiepilogo extends Controller {

    @FXML
    private VBox secondPane;

    @FXML
    private TextField searchField;

    @FXML
    ToolBar toolBar;

    @FXML
    private Button buttonNuovo;

    @FXML
    private Button buttonModifica;

    @FXML
    private Button buttonRimuovi;

    @FXML
    private Button buttonDettagli;

    @FXML
    private TableView<ModelClass> table;


    private final FrontController fc = FCFactory.getFC();

    String riepilogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(String request) {
        //TODO da rivedere
        riepilogo = request;

        switch (riepilogo) {
            case "contratto":
                searchField.setPromptText("Cerca Contratto");
                searchField.setTooltip(new Tooltip("cerca per Nome, Cognome cliente o Targa auto"));
                toolBar.getItems().remove(2);
                break;

            case "prenotazione":
                searchField.setPromptText("Cerca Prenotazione");
                searchField.setTooltip(new Tooltip("cerca per Nome, Cognome cliente o Targa auto"));
                break;

            case "cliente":
                searchField.setPromptText("Cerca Cliente");
                searchField.setTooltip(new Tooltip("cerca per Nome, Cognome o Codice Fiscale"));
                toolBar.getItems().remove(2);
                initTableClienti();
                break;

            case "auto":
                searchField.setPromptText("Cerca Auto");
                searchField.setTooltip(new Tooltip("cerca per Targa, Marca o Modello"));

                if (runningData.get("tipo").equals("Dipendente")) {
                    toolBar.getItems().remove(0);
                    toolBar.getItems().remove(1);

                } else if (runningData.get("tipo").equals("AdminSistema")) {
                    toolBar.getItems().remove(0);
                    toolBar.getItems().remove(0);
                    toolBar.getItems().remove(0);
                    toolBar.getItems().remove(0);
                }

                break;

            case "fattura":
                searchField.setPromptText("Cerca Fattura");
                searchField.setTooltip(new Tooltip("cerca per Nome cliente oppure Targa auto"));
                toolBar.getItems().remove(2);
                break;

        }
    }

    public void onNuovo(ActionEvent event) {
        try {
            switch (riepilogo) {
                case "contratto":
                    fc.processRequest("showNuovoContratto", runningData);
                    break;

                case "prenotazione":
                    fc.processRequest("showNuovoPrenotazione", runningData);
                    break;

                case "cliente":
                    fc.processRequest("showNuovoCliente", runningData);
                    break;
            }



            refreshTableClienti();

        } catch (CommonException e) {
            e.reportException();
        }



    }

    public void onModifica(ActionEvent event) {
        Packet packet = new Packet();
        try {
            switch (riepilogo) {
                case "contratto":
                    packet.set("id",  table.getFocusModel().getFocusedItem().getId());
                    TransferObject contratto = TOFactory.getTO("TOContratto");//(TransferObject) fc.processRequest("readContratto", packet);
                    //TODO VEDERE packet al posto di list o in più
                    //TODO da controllare dopo
                    //packet = new Packet();
                    packet.set("transfer", contratto);
                    fc.processRequest("showModificaContratto", packet);
                    break;

                case "cliente":
                    packet.set("id",  table.getFocusModel().getFocusedItem().getId());
                    TransferObject cliente = (TransferObject) fc.processRequest("readCliente", packet);
                    //TODO VEDERE packet al posto di list o in più
                    //TODO da controllare dopo
                    //packet = new Packet();
                    packet.set("transfer", cliente);
                    fc.processRequest("showModificaCliente", packet);
                    break;
            }


            refreshTableClienti();

        } catch (CommonException e) {
            e.reportException();
        }




    }

    public void onMostraDettagli(ActionEvent event) {
        Packet packet = new Packet();
        try {
            switch (riepilogo) {
                case "cliente":
                    packet.set("id",  table.getFocusModel().getFocusedItem().getId());
                    TransferObject cliente = (TransferObject) fc.processRequest("readCliente", packet);
                    //TODO VEDERE packet al posto di list o in più
                    //TODO da controllare dopo
                    //packet = new Packet();
                    packet.set("transfer", cliente);
                    fc.processRequest("showDettagliCliente", packet);
                    break;
            }


            refreshTableClienti();

        } catch (CommonException e) {
            e.reportException();
        }
    }

    public void onCerca(ActionEvent event) {
        if (searchField.getText().isEmpty()) {
            searchField.setPromptText("Inserire un criterio di ricerca");
        } else {
            Packet packet = new Packet();
            packet.set("findKey", searchField.getText());
            try {
                List<TransferObject> list = (List<TransferObject>) fc.processRequest("findCliente", packet);
                refreshTableClienti(list);
            } catch (CommonException e) {
                e.reportException();
            }
        }

    }

    private void initTableClienti() {

        TableColumn<ModelClass, String> first = new TableColumn<>();
        TableColumn<ModelClass, String> second = new TableColumn<>();
        TableColumn<ModelClass, String> third = new TableColumn<>();
        TableColumn<ModelClass, String> fourth = new TableColumn<>();
        TableColumn<ModelClass, String> fifth = new TableColumn<>();
        TableColumn<ModelClass, String> sixth = new TableColumn<>();

        first.setText("ID");
        second.setText("NOME");
        third.setText("COGNOME");
        fourth.setText("NR. PATENTE");
        fifth.setText("E-MAIL");
        sixth.setText("CODICE FISCALE");

        first.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        second.setCellValueFactory(cellData -> cellData.getValue().column1Property());
        third.setCellValueFactory(cellData -> cellData.getValue().column2Property());
        fourth.setCellValueFactory(cellData -> cellData.getValue().column3Property());
        fifth.setCellValueFactory(cellData -> cellData.getValue().column4Property());
        sixth.setCellValueFactory(cellData -> cellData.getValue().column5Property());

        table.getColumns().addAll(first, second, third, fourth, fifth, sixth);

        refreshTableClienti();
        //setTableRowEvents(tabellaCliente, "ShowDettagli", new Pacchetto());


    }

    private void refreshTableClienti() {
        try {
            List<TransferObject> list = (List<TransferObject>) fc.processRequest("readAllClienti", new Packet());

            ObservableList<ModelClass> tableData = FXCollections.observableArrayList();

            for(TransferObject to:list) {
                TOCliente cliente = (TOCliente) to;
                tableData.add(new ModelClassCliente(cliente.getId(), cliente.getNome(), cliente.getCognome(),
                        cliente.getNumeroPatente(), cliente.getEmail(), cliente.getCodiceFiscale()));
            }

            table.setItems(tableData);
        } catch (CommonException e) {
            e.reportException();
        }

    }

    private void refreshTableClienti(List<TransferObject> toList) {

        ObservableList<ModelClass> tableData = FXCollections.observableArrayList();

        for(TransferObject to:toList) {
            TOCliente cliente = (TOCliente) to;
            tableData.add(new ModelClassCliente(cliente.getId(), cliente.getNome(), cliente.getCognome(),
                    cliente.getNumeroPatente(), cliente.getEmail(), cliente.getCodiceFiscale()));
        }

        table.setItems(tableData);
    }
}
