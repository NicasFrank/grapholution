package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * SceneController Klasse für die Darstellung des Konfigurierbaren Hillclimbers
 */
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
   * Methode um Hillclimber Algorithmus mit entsprechendem ViewModel
   */
  public void sendButton_startAlgo() {
      viewModel.navigation_configureScreen("StatisticsHillclimber");
  //    viewModel.startHillclimberAlgorithm(
  //            radioMutationBinary.isSelected(),
  //            radioMutationBinary.isSelected() ? sliderMutationChance.getValue() : 0,
  //            -1,

  //    );

 }

  /**
   * Handlet die Rückwärtsnavigation
   * */
  public void sendButton_backwards(){ viewModel.navigation_Back(); }
  /**
  * setter für allgemeines ViewModel zur Navigation
  * @param viewModel
  */
  public void setViewModel(ViewModel viewModel){ this.viewModel=viewModel; }
}
