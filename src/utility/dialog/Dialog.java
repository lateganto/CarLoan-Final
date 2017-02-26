package utility.dialog;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * Created by salvatore on 12/10/15.
 */
public class Dialog {

    public static void showDetailedErrorDialog(String header, String content) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    public static void showErrorDialog(String error) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Operazione fallita");
        alert.setContentText(error);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    public static void showInformationDialog(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText("Operazione completata con successo");
        alert.setContentText(msg);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    /*public static void showConfirmationDialog(String msg) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(msg);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }*/

}
