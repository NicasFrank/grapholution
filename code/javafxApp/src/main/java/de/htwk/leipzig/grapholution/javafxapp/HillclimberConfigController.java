package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HillclimberConfigController {
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

  public TextField getInput(){return inputField;}

  public void setInputField(){inputField.setText(viewModel.getTest());}
  public void setViewModel(ViewModel viewModel){this.viewModel=viewModel;}
}
