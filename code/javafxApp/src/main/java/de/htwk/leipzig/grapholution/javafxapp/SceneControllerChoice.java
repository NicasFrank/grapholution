package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class SceneControllerChoice extends SceneController{
  @FXML
  private Button nextButton,loadButton;
  @FXML
  private ComboBox<String> comboBoxAlgo;
  @FXML
  private ComboBox<String> comboBoxProblem;


  private final String[] algorythms = {"Hillclimber","Ein Anderer", "Noch Einer"};
  private ViewModel viewModel;

  /**
   * wird aufgerufen, sobald instanze erstellt
   * setzt Inhalt der ComboBox(en), erstellt ViewModel und gibt diesem sich selbst als parameter
   */
  public void initialize(){
    comboBoxAlgo.getItems().setAll(algorythms);
  }

  /**
   * gibt Auswahl des Algorithmus an viewmodel weiter
   */
  public void sendButtonClick_configureScreen() {
    viewModel.navigation_configureScreen(
        comboBoxAlgo.getValue()
        //,comboBoxProblem.getValue()
    );
  }

  public void setViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
  }
}
