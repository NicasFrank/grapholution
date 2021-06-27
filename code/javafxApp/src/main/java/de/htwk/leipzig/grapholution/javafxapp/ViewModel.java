package de.htwk.leipzig.grapholution.javafxapp;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class ViewModel {

    private StringProperty inputField = new SimpleStringProperty();
    private StringProperty outputField= new SimpleStringProperty();

    /**
     * Handhabung des Button-Klicks:
     * outputField wird anfangs geleert
     * bei Klick wird Inhalt des Eingabefelds im Ausgabefeld geholt
     */
    public void onButtonClick(){
        outputField.set("");
        if(isInputCorrect()) {
            writeResultInGUI();
        } else {
            alert();
        }
    }

    /**
     * Handhabung des Eingabefeldes:
     * @return: true falls Eingabe 0 oder 1 (Buchstabe)
     * iteriert durch das Eingabefeld und speichert in einem char Array
     */
    private boolean isInputCorrect(){
        char[] input = inputField.get().toCharArray();
        for (int i=0; i<input.length;i++) {
            if (input[i] != '0' && input[i] != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Gibt Fehlermeldung falls Eingabe nicht dem obigen Format entspricht
     */
    private void alert(){
        outputField.set("Nur 1 oder 0 verwenden!");
    }

    /**
     * Handhabung des Ausgabefeldes:
     * setzt Inhalt des Eingabefeldes in das Ausgabefeld
     * und leert das Eingabefeld
     */
    private void writeResultInGUI(){
        outputField.set(inputField.get());
        inputField.set("");
    }

    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     * @return
     */
    public Property<String> inputFieldProperty() {
        return inputField;
    }

    public ObservableValue<String> outputFieldProperty() {
        return outputField;
    }
}
