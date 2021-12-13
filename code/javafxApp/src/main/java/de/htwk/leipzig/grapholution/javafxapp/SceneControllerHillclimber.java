package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SceneControllerHillclimber extends SceneController{
  @FXML
  private TextField inputField;
  @FXML
  private RadioButton radioMutationBinary;
  @FXML
  private Slider sliderMutationChance;

  private ViewModel viewModel;

  /**
   * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
   * an Hillclimber weiter
   */
  public void sendButton_startAlgo() {
      viewModel.navigation_configureScreen("StatisticsHillclimber");
      //viewModel.startHillclimberAlgorithm();
  }

  public void sendButton_backwards(){ viewModel.navigation_Back(); }

  public void setViewModel(ViewModel viewModel){ this.viewModel=viewModel; }
}
