package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.hillclimber.Hillclimber;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewModel {

  private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private final SceneControllerBase sceneControllerBase;
  private Pane[] allScenes = new Pane[3];
  private int currentScene = -1;

  private Hillclimber hilly;

  ViewModel(SceneControllerBase sceneControllerBase, Pane firstPane){
    this.sceneControllerBase = sceneControllerBase;
    allScenes[0]=firstPane;
  }

  /**
   * switch anhand String je nach nächster Pane
   * @param nameOfNextScreen String mit Namen des nächsten Screens
   */
  public void navigation_configureScreen (Object nameOfNextScreen){
    currentScene++;
    switch (nameOfNextScreen.toString()){
      case "Choice":
        allScenes[0] = loadNewPane("WahlAlgorithmus.fxml");
        break;

      case "Hillclimber":
        allScenes[1] = loadNewPane("ConfigHillclimber.fxml");
        break;

      case "Ein Anderer":
        //Something
        break;

      case "Auswertung":
        allScenes[2] = loadNewPane("AuswertungScreen.fxml");
        break;

      default:
        //Nichts
        break;
    }
  }

  /**
   * dient zur Rückwärtsnavigation
   * anhand der aktuellen szene wird aus array die vorherige genommen
   */
  public void navigation_Back(){
    currentScene--;
    setNextScreen(allScenes[currentScene]);
  }
  /**
   * Hillclimber instanziert und ausgeführt
   * @param startConfig Startkonfiguration
   */
  public void climbTheHill(String startConfig){
    hilly = new Hillclimber(startConfig);
    outputField.set(hilly.hillclimb());
  }
  /**
   * versucht bestimmte Pane zu laden
   * @param paneName Name der zu ladenden Pane aus private String[] slides
   * @return wahr wenn erfolgreich false wenn nicht
   */
    private Pane loadNewPane (String paneName){
      FXMLLoader loader = new FXMLLoader(getClass().getResource(paneName));
      try {
        Pane nextPane = loader.load();
        SceneController newController = loader.getController();
        newController.setViewModel(this);
        setNextScreen(nextPane);
        return nextPane;
      } catch (IOException e) {
        return null;
      }
    }

  /**
   * gibt nächste Scene an ersten Controller weiter
   * @param nextScreen nächste Scene als Pane
   */
    private void setNextScreen (Pane nextScreen){
      if(nextScreen!=null){
        sceneControllerBase.setNewScreen(nextScreen);
      } else {
        //Fehlermeldung
      }
    }

  /**
   * Handhabung des Eingabefeldes:
   * iteriert durch das Eingabefeld und speichert in einem char Array
   * @return true falls Eingabe 0 oder 1 (Buchstabe)
   */
  private boolean isInputCorrect(){
        char[] input = inputField.get().toCharArray();
        for (char c : input) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     **/
    public Property<String> outputFieldProperty() {
        return outputField;
    }
}
