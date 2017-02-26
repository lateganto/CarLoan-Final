package presentation.boundary.controller;

import javafx.fxml.Initializable;
import utility.Packet;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by salvatore on 19/10/15.
 */
public abstract class Controller implements Initializable {

    @Override
    public abstract void initialize(URL location, ResourceBundle resources);

    protected Packet runningData;

    public abstract void start(String request);

    public void initData(Packet runningData) {
        this.runningData = runningData;
    }

    //mettere nel diagramma UML
    public Packet getRunningData(){
        return runningData;
    }

}
