package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SCBase extends SceneController{
  @FXML
  private Pane basePane;

  private ViewModel viewModel;

  public void initialize(){
    this.viewModel = new ViewModel(this, basePane);
    viewModel.navigation_configureScreen(EChoices.AlgorithmChoice);
  }
  /**
   * löscht die aktuellen elemente der szene und fügt die neuen hinzu
   * @param newPane die Pane, die zur neuen Szene werden soll
   */
  public void setNewScreen(Pane newPane){
    if (newPane!=null) {
      basePane.getChildren().clear();
      basePane.getChildren().add(newPane);
    } else {
    throw new IllegalArgumentException("Pane is Null!");
  }
  }
}
