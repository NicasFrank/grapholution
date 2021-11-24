package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SceneControllerHillclimber extends SceneController{
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
  private SceneControllerResults sceneControllerResults;

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
  }
}
