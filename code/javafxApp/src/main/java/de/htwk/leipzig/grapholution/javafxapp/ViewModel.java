package de.htwk.leipzig.grapholution.javafxapp;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewModel {
  private String[] slides = {"firstSmallScreen.fxml","secondSmallScreen.fxml","lastSmallScreen.fxml"};
  private int currentSlide = 0;
  private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private String test;
    /**
     * Handhabung des Navigation-Button-Klicks:
     * geprüft ob gewünscht Richtung der Navigation erlaubt ist wenn nein, return null
     * wenn ja wird int-Wert des Buttons mit currentSlide verrechnet um feststellen zu koennen welche Pane aktiv ist
     * und diese wird geladen
     */
    public Pane onButtonClick_slideNavigation(int buttonClicked){
      if ((currentSlide<=0 && buttonClicked<0) || (currentSlide>=2 && buttonClicked>0)){
        return null;
      }
      currentSlide+=buttonClicked;
      return loadNewPane(slides[currentSlide]);
    }

  /**
   * versucht bestimmte Pane zu laden und gibt entweder diese oder null zurück
   * @param paneName Name der zu ladenden Pane aus private String[] slides
   */
    private Pane loadNewPane (String paneName){
      try {
        return FXMLLoader.load(getClass().getResource(paneName));
      } catch (IOException e) {
        return null;
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
     * Gibt Fehlermeldung falls Eingabe nicht dem obigen Format entspricht
     **/
    private void alert(){
        outputField.set("Nur 1 oder 0 verwenden!");
    }

    /**
     * Handhabung des Ausgabefeldes:
     * setzt Inhalt des Eingabefeldes in das Ausgabefeld
     * und leert das Eingabefeld
     **/
    private void writeResultInGUI(){
        outputField.set(inputField.get());
        inputField.set("");
    }

    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     **/
    public Property<String> inputFieldProperty() {
        return inputField;
    }
    public ObservableValue<String> outputFieldProperty() {
        return outputField;
    }


    public void setTest(String test){
      this.test=test;
    }
    public String getTest (){
      return this.test;
    }
}
