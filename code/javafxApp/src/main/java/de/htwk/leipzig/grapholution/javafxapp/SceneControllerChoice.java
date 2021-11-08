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
  @FXML
  private Pane basePane;

  private final String[] algorythms = {"Hillclimber","Ein Anderer", "Noch Einer"};
  private ViewModel viewModel;

  /**
   * wird aufgerufen, sobald instanze erstellt
   * setzt Inhalt der ComboBox(en), erstellt ViewModel und gibt diesem sich selbst als parameter
   */
  public void initialize(){
    comboBoxAlgo.getItems().setAll(algorythms);
    this.viewModel = new ViewModel(this);
  }

  /**
   * gibt Auswahl des Algorithmus an viewmodel weiter
   */
  public void sendButtonClick_configureScreen() {
    boolean didItWork = viewModel.navigation_configureScreen(
        comboBoxAlgo.getValue()
        //,comboBoxProblem.getValue()
    );
  }

  /**
   * löscht die aktuellen elemente der szene und fügt die neuen hinzu
   * @param newPane die Pane, die zur neuen Szene werden soll
   * @return true wenn neue Szene gesetzt werden kann  false wenn nicht
   */
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
