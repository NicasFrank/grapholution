package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
  public void sendButton_startAlgo(){
    viewModel.navigation_configureScreen("StatisticsHillclimber");
    viewModel.climbTheHill(
            inputField.getText(),
            radioMutationBinary.isSelected() ? sliderMutationChance.getValue() : 0);
  }

  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
    inputField.textProperty().bindBidirectional(viewModel.inputFieldProperty());
  }
}
