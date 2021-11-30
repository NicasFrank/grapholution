package de.htwk.leipzig.grapholution.javafxapp;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testfx.api.FxRobot;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

class SceneControllerBaseTest
       extends FxRobot
{

 @Mock
 ViewModel viewModel;

 SceneControllerBase sceneControllerBase;

 @Test
 void start() {
   Platform.startup(()-> {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("basePane.fxml"));
     Pane pane = null;

     try {
       pane = loader.load();
     }
     catch (IOException e) {

     }

     sceneControllerBase = loader.getController();
     sceneControllerBase.setViewModel(viewModel);
     Stage stage = new Stage();
     Scene scene = new Scene(pane);
     stage.setScene(scene);
     stage.show();

     Pane child = new Pane();

     sceneControllerBase.setNewScreen(child);

     verifyThat(child,isVisible());

   });
 }
}