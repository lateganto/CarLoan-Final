package presentation.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.Packet;

/**
 * Created by salvatore on 11/10/15.
 */
public class SchermataLogin extends Application implements Boundary {
    private static final String LOGIN_PATH = "/presentation/boundary/fxml/Login.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Class<?> mainClass = getClass();
        String fxmlpath = LOGIN_PATH;
        Parent root = FXMLLoader.load(mainClass.getResource(fxmlpath));

        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);

        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public Object showWindow(Packet packet) {
        launch();
        return null;
    }

    public static void main(String... args) {
        launch(args);
    }
}
