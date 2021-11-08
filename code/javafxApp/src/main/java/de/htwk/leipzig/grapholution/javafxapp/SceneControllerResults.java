package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SceneControllerResults extends SceneController{

  @FXML
  private TextField outputField;

  private ViewModel viewModel;

  /**
   * setter für viewmodel und bindet outputfield an output vom viewmodel
   * @param viewModel
   */
  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
    outputField.textProperty().bind(viewModel.outputFieldProperty());
  }
}
