package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.hillclimber.Hillclimber;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class ViewModel {

  private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private SceneControllerChoice sceneControllerChoice;

  private Hillclimber hilly;

  ViewModel(SceneControllerChoice sceneControllerChoice){
    this.sceneControllerChoice = sceneControllerChoice;
  }

  /**
   * switch anhand String je nach nächster Pane
   * @param nameOfNextScreen String mit Namen des nächsten Screens
   * @return bool ob laden erfolgreich oder nicht
   */
  public boolean navigation_configureScreen (Object nameOfNextScreen){
    boolean result=false;
    switch (nameOfNextScreen.toString()){
      case "Hillclimber":
        result = loadNewPane("secondSmallScreen.fxml");
        break;

      case "Ein Anderer":
        //Something
        break;

      case "Auswertung":
        result = loadNewPane("lastSmallScreen.fxml");
        break;

      default:
        //Nichts
        break;
    }
    return result;
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
    private boolean loadNewPane (String paneName){
      FXMLLoader loader = new FXMLLoader(getClass().getResource(paneName));
      try {
        Pane nextPane = loader.load();
        SceneController newController = loader.getController();
        newController.setViewModel(this);
        return setNextScreen(nextPane);
      } catch (IOException e) {
        return false;
      }
    }

  /**
   * gibt nächste Scene an ersten Controller weiter
   * @param nextScreen nächste Scene als Pane
   * @return wahr wenn erfolgreich false wenn nicht
   */
    private boolean setNextScreen (Pane nextScreen){
      if(nextScreen!=null){
        return sceneControllerChoice.setNewScreen(nextScreen);
      } else {
        //Fehlermeldung
        return false;
      }
    }

    /**
     * Handhabung des Eingabefeldes:
     * @return: true falls Eingabe 0 oder 1 (Buchstabe)
     * iteriert durch das Eingabefeld und speichert in einem char Array
     **/
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

    public List<String> getHistory (){
      return hilly.getHistory();
    }
}
