package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.hillclimber.Hillclimber;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewModel {

  //private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private StartController startController;
  private Hillclimber hilly;

  ViewModel(StartController startController){
    this.startController=startController;
  }

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

  public void climbTheHill(String startConfig){
    hilly = new Hillclimber(startConfig);
    outputField.set(hilly.hillclimb());
  }
  /**
   * versucht bestimmte Pane zu laden und gibt entweder diese oder null zurück
   * @param paneName Name der zu ladenden Pane aus private String[] slides
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

    private boolean setNextScreen (Pane nextScreen){
      if(nextScreen!=null){
        return startController.setNewScreen(nextScreen);
      } else {
        //Fehlermeldung
        return false;
      }
    }

    /**
     * Handhabung des Eingabefeldes:
     * @return: true falls Eingabe 0 oder 1 (Buchstabe)
     * iteriert durch das Eingabefeld und speichert in einem char Array
     **
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
     * Gibt Fehlermeldung falls Eingabe nicht dem obigen Format entspricht
     **
    private void alert(){
        outputField.set("Nur 1 oder 0 verwenden!");
    }

    /**
     * Handhabung des Ausgabefeldes:
     * setzt Inhalt des Eingabefeldes in das Ausgabefeld
     * und leert das Eingabefeld
     **
    private void writeResultInGUI(){
        outputField.set(inputField.get());
        inputField.set("");
    }

    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     **
    public Property<String> inputFieldProperty() {
        return inputField;
    }
    */
    public ObservableValue<String> outputFieldProperty() {
        return outputField;
    }

}
