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
    /**
     * Methode bindet die fxml datei sowie die css Datei in den Startprozess ein
     * @param stage: Benutzt um Titel und Scene zu setzen
     * @throws Exception: Wenn Datei nicht gefunden wurde
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
