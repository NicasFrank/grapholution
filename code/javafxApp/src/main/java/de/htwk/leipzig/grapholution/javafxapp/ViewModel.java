package de.htwk.leipzig.grapholution.javafxapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewModel {

    private TextField inputField;
    private TextField outputField;
    private Button startButton;

    public ViewModel(){
        startButton = new Button();
        outputField = new TextField();
        inputField = new TextField();
    }

    private void isButtonClicked(){
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                /**Aktion für starten des Hillclimber Algorithmus;

                 */
                 }
        });
    }

    private void handleInput(){

    }

    private void handleOutput(){

    }
}
