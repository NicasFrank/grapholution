package de.htwk.leipzig.grapholution.javafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class StartController extends SceneController{
  @FXML
  private Button nextButton,loadButton;
  @FXML
  private ComboBox comboBoxAlgo,comboBoxProblem;
  @FXML
  private Pane basePane;

  private String[] algorythms = {"Hillclimber","Ein Anderer", "Noch Einer"};
  private ViewModel viewModel;

  public void initialize(){
    comboBoxAlgo.getItems().setAll(algorythms);
    this.viewModel = new ViewModel(this);
  }

  public void sendButtonClick_configureScreen(ActionEvent actionEvent) {
    boolean didItWork = viewModel.navigation_configureScreen(
        comboBoxAlgo.getValue()
        //,comboBoxProblem.getValue()
    );
  }

  public boolean setNewScreen(Pane newPane){
    if (newPane!=null) {
      basePane.getChildren().clear();
      basePane.getChildren().add(newPane);
      return true;
    } else {
      return false;
    }
  }
}
