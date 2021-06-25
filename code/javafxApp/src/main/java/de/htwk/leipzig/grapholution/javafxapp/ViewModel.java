package de.htwk.leipzig.grapholution.javafxapp;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class ViewModel {

    private StringProperty inputField = new SimpleStringProperty();
    private StringProperty outputField= new SimpleStringProperty();;

    public void onButtonClick(){
        outputField.set("");
        if(handleInput()) {
            handleOutput();
        } else {
            alert();
        }
    }

    private boolean handleInput(){
        char[] input = inputField.get().toCharArray();
        for (int i=0; i<input.length;i++) {
            if (input[i] != '0' && input[i] != '1') {
                return false;
            }
        }
        return true;
    }

    private void alert(){
        outputField.set("Input nicht richtig Format!");

    }

    private void handleOutput(){
        outputField.set(inputField.get());
        inputField.set("");
    }

    public Property<String> inputFieldProperty() {
        return inputField;
    }

    public ObservableValue<String> outputFieldProperty() {
        return outputField;
    }
}
