package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class SCHillclimber extends SceneController{
  public Slider slider;
  @FXML
  private TextField inputField;
  @FXML
  private RadioButton radioCon1op1;
  @FXML
  private RadioButton radioCon1op2;
  @FXML
  private RadioButton radioCon1op3;
  @FXML
  private ToggleGroup Config1;
  @FXML
  private ToggleGroup Config2;

  private ViewModel viewModel;
  private SCResultsHillclimber sCResultsHillclimber;

  /**
   * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
   * an Hillclimber weiter
   */
  public void sendButton_startAlgo(){
    viewModel.navigation_configureScreen(EChoices.ResultsHillclimber);
    viewModel.climbTheHill(inputField.getText());
  }

  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
    inputField.textProperty().bindBidirectional(viewModel.inputFieldProperty());
    setOptions(viewModel.getConfigOptions());
  }

  private void setOptions(AlgorithmConfigOptions options) {
    slider.valueProperty().set(options.getOrElse(IntConfig.Limit, (int) slider.getValue()));
  }

  private AlgorithmConfigOptions createConfigOptions() {
    return new AlgorithmConfigOptions()
            .add(IntConfig.Limit, (int) slider.getValue());
  }

  public void sendButton_saveConfig() {
      var fileChooser = new FileChooser();
      fileChooser.getExtensionFilters()
              .add(new FileChooser.ExtensionFilter("Hillclimber (*.hccf)", "*.hccf"));

      var file = fileChooser.showSaveDialog(null);

      if (file != null) {
        try {
          createConfigOptions().serialize(file);
        } catch (Exception e) {
          DialogUtils.ShowAlert("Error", "Fehler beim Speichern der Datei!");
        }
      }
  }
}
