package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.javafxapp.enums.EChoices;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

import java.util.stream.Collectors;

public class SceneControllerChoice extends SceneController {
  public static final String ZERO_MAX = "Zero Max";
  public static final String ONE_MAX = "One Max";

  @FXML
  private ComboBox<String> comboBoxAlgo;
  @FXML
  private ComboBox<String> comboBoxProblem;

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
    comboBoxAlgo.getSelectionModel().select(0);
    comboBoxProblem.getItems().setAll(ONE_MAX, ZERO_MAX);
    comboBoxProblem.getSelectionModel().select(0);
  }

  /**
   * gibt Auswahl des Algorithmus an viewmodel weiter
   */
  public void sendButtonClick_configureScreen() {
    viewModel.setProblemIsOneMax(comboBoxProblem.getValue().equals(ONE_MAX));
    viewModel.navigation_configureScreen(
        EChoices.getByName(comboBoxAlgo.getValue())
    );
  }

  public void sendButton_loadConfig() {
    var fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Evolutionaere Algorithmen (*.gacf, *.hccf)", "*.gacf", "*.hccf"));

    var file = fileChooser.showOpenDialog(null);

    if (file != null) {
      try {
        var options = new AlgorithmConfigOptions().deserialize(file);

        var splitName = file.getName().split("\\.");
        var ending = splitName[splitName.length - 1];
        comboBoxProblem.getSelectionModel().select(options.getOrElse(BoolConfig.FitnessIsOneMax, true) ? ONE_MAX : ZERO_MAX);
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
