package sample;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLTest extends Application {

    public static void main(String[] args) {
        Application.launch(FXMLTest.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlURL = Paths.get("C:\\Users\\Jan\\IdeaProjects\\mvvmfx\\sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(fxmlURL);

        stage.setTitle("FXML Welcome");
        Scene myScene = new Scene(root, 300, 275);
        stage.setScene(myScene);
        stage.show();
    }
}