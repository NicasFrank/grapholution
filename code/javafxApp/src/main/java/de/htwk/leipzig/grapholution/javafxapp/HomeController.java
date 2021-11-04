package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HomeController {
    @FXML
    private Pane contentPane;

    private final ViewModel viewModel = new ViewModel();

    /**
     * @initialize :
     * Versucht erste inhaltliche Pane (Algorithmuswahl) zu laden
     **/
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstSmallScreen.fxml"));
        Parent p = null;
        try {
             p = loader.load();
        } catch (IOException e) {}

        StartController startController = loader.getController();
        startController.setViewModelAndController(viewModel,this);

        contentPane.getChildren().add(p);
    }

    public void setNextPane(Pane nextPane){
        contentPane.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondSmallScreen.fxml"));
        Parent p = null;
        try {
            p = loader.load();
        } catch (IOException e) {}

        HillclimberConfigController hillClimberConfigController = loader.getController();
        hillClimberConfigController.setViewModel(viewModel);

        contentPane.getChildren().add(p);
    }
    /**
     * @param actionEvent: Klick event der Navigation Buttons (Zurueck und Weiter)
     *
    public void sendButtonClick_slideNavigation(ActionEvent actionEvent) {
        Pane nextPane=sendButtonClick_getNextPane(actionEvent);
        if(nextPane==null){
            return;
        } else {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(nextPane);
        }
    }
    */

    /**
     * unterscheidet den geklickten Navigations-Button und gibt entsprechenden int Wert an das ViewModel weiter
     * @param actionEvent Klickevent des Buttons
     * @return entweder die entsprechende Pane oder null
     *
    private Pane sendButtonClick_getNextPane(ActionEvent actionEvent){
        if(actionEvent.getSource().equals(backButton)){
            return viewModel.onButtonClick_slideNavigation(-1);
        }
        else if(actionEvent.getSource().equals(nextButton)) {
            return viewModel.onButtonClick_slideNavigation(1);
        }
        return null;
    }
    */
}