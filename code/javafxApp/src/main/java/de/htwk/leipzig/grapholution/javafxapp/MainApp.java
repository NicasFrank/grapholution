package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.javafxapp.sceneController.SceneControllerChoice;
import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {
    /**
     * Methode bindet die fxml datei sowie die css Datei in den Startprozess ein
     * @param stage: Benutzt um Titel und Scene zu setzen
     * @throws Exception Falls ein unbehandelter Fehler beim Laden der fxml-Datei auftritt
     */
    @Override
    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("AlgorithmChoice.fxml"));
        Pane pane = loader.load();
        SceneControllerChoice controller = loader.getController();

        controller.initialize();
        var scene = new Scene(pane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles_new.css")).toExternalForm());

        controller.setViewModel(new ViewModel(stage, scene));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
