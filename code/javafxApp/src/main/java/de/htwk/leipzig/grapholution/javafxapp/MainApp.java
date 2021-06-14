package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.list.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.text.WordUtils;

import static de.htwk.leipzig.grapholution.evolibrary.utilities.StringUtils.join;
import static de.htwk.leipzig.grapholution.evolibrary.utilities.StringUtils.split;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        String title = produceTitleUsingLibrary();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private String produceTitleUsingLibrary() {
        LinkedList tokens;
        tokens = split(MessageUtils.getMessage());
        String result = join(tokens);
        return WordUtils.capitalize(result);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
