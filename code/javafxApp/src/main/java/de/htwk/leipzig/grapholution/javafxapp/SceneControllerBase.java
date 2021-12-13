package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SceneControllerBase extends SceneController{
  @FXML
  private Pane basePane;

  private ViewModel viewModel;

  public void initialize(){
    this.viewModel = new ViewModel(this, basePane, 12,true,true);
    viewModel.navigation_configureScreen("Choice");
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
