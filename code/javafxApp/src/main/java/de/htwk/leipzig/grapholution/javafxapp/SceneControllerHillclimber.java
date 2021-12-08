package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmType;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SceneControllerHillclimber extends SceneController{
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
  private SceneControllerResultsHillclimber sceneControllerResultsHillclimber;

  /**
   * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
   * an Hillclimber weiter
   */
  public void sendButton_startAlgo(){
    viewModel.navigation_configureScreen("AuswertungHillclimber");
    viewModel.climbTheHill(inputField.getText());
  }

  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
    inputField.textProperty().bindBidirectional(viewModel.inputFieldProperty());
  }

  private AlgorithmConfigOptions createConfigOptions() {
    var options = new AlgorithmConfigOptions();
    options.add("limit", Math.round(slider.getValue()));

    return options;
  }

  public void sendButton_saveConfig(ActionEvent actionEvent) {
      var fileChooser = new FileChooser();
      fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json files", "*.json"));

      var file = fileChooser.showSaveDialog(null);

      if (file != null) {

        FileOutputStream fos = null;
        try {
          fos = new FileOutputStream(file);
          var oos = new ObjectOutputStream(fos);

          oos.writeObject(AlgorithmType.Hillclimber);
          oos.writeObject(createConfigOptions());

          oos.close();
          fos.close();
        } catch (Exception e) {
          DialogUtils.ShowAlert("Error", "Fehler beim Speichern der Datei!");
        };
      }
  }
}
