package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SceneControllerChoice extends SceneController{
  @FXML
  private Button nextButton,loadButton;
  @FXML
  private ComboBox<String> comboBoxAlgo;
  @FXML
  private ComboBox<String> comboBoxProblem;



  private ViewModel viewModel;

  /**
   * wird aufgerufen, sobald instanze erstellt
   * setzt Inhalt der ComboBox(en), erstellt ViewModel und gibt diesem sich selbst als parameter
   */
  public void initialize(){
    comboBoxAlgo.getItems().setAll(
    Arrays.stream(AlgorithmType.class.getEnumConstants())
            .map(algorithmType -> algorithmType.name)
            .collect(Collectors.toList())
    );
  }

  /**
   * gibt Auswahl des Algorithmus an viewmodel weiter
   */
  public void sendButtonClick_configureScreen() {
    viewModel.navigation_configureScreen(
        EChoices.valueOf(comboBoxAlgo.getValue())
        //,comboBoxProblem.getValue()
    );
  }

  public void setViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
  }

  public void sendButton_loadConfig(ActionEvent actionEvent) {
    var fileChooser = new FileChooser();
    fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json files", "*.json"));

    var file = fileChooser.showOpenDialog(null);

    if (file != null) {
      viewModel.navigation_configureScreen(
              EChoices.valueOf(comboBoxAlgo.getValue()),
              file
      );
    }
  }
}
