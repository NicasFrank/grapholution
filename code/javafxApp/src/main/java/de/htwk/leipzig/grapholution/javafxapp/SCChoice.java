package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.util.stream.Collectors;

public class SCChoice extends SceneController{
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
      EChoices.algorithms().stream()
              .map(eChoice -> eChoice.name)
              .collect(Collectors.toList())
    );
  }

  /**
   * gibt Auswahl des Algorithmus an viewmodel weiter
   */
  public void sendButtonClick_configureScreen() {
    viewModel.navigation_configureScreen(
        EChoices.getByName(comboBoxAlgo.getValue())
        //,comboBoxProblem.getValue()
    );
  }

  public void setViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
  }

  public void sendButton_loadConfig(ActionEvent actionEvent) {
    var fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Evolutionaere Algorithmen (*.gacf, *.hccf)", "*.gacf", "*.hccf"));

    var file = fileChooser.showOpenDialog(null);

    if (file != null) {
      try {
        var options = new AlgorithmConfigOptions().deserialize(file);

        var splitName = file.getName().split("\\.");
        var ending = splitName[splitName.length - 1];
        viewModel.setConfigOptions(options);
        switch (ending) {
          case "gacf":
            viewModel.navigation_configureScreen(EChoices.GeneticAlgorithm);
            break;
          case "hccf":
            viewModel.navigation_configureScreen(EChoices.Hillclimber);
            break;
        }
      } catch (Exception e) {
        DialogUtils.ShowAlert("Error", "Fehler beim Öffnen der Datei!");
      }
    }
  }
}
