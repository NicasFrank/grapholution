package de.htwk.leipzig.grapholution.javafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StartController {
  @FXML
  private Button nextButton,loadButton;
  private HomeController homeController;
  private ViewModel viewModel;

  public void sendButtonClick_nextPane(ActionEvent actionEvent){
    homeController.setNextPane(sendButtonClick_slideNavigation(actionEvent));
  }

  private Pane sendButtonClick_slideNavigation(ActionEvent actionEvent) {
    if(actionEvent.getSource().equals(nextButton)) {
      return viewModel.onButtonClick_slideNavigation(1);
    }
    return null;
  }

  public void setViewModelAndController(ViewModel viewModel, HomeController homeController) {
    this.viewModel = viewModel;
    this.homeController = homeController;
  }

  public void setTextforModel(){
    viewModel.setTest("Whasfnjknas");
  }
}
