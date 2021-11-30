package de.htwk.leipzig.grapholution.javafxapp;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotInterface;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isFocused;

class SceneControllerChoiceTest
       extends ApplicationTest implements FxRobotInterface
{

 @Mock
 ViewModel viewmodel;

 Pane basePane = new Pane();

 @AfterEach
 public void tearDown () throws Exception {
   FxToolkit.hideStage();
   release(new KeyCode[]{});
   release(new MouseButton[]{});
 }

 @Override
 public void start(Stage stage){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("WahlAlgorithmus.fxml"));
     Parent pane;
     try {
       pane = loader.load();
       SceneController cont = loader.getController();
       cont.setViewModel(viewmodel);
       basePane.getChildren().add(pane);
       Scene scene = new Scene(basePane);
       stage.setScene(scene);
       stage.show();
     } catch (IOException e) {
       e.printStackTrace();
     }
 }

 @Test
 public void test(){

   ComboBox<String> cb=null;
   System.out.println(basePane.getChildren().toString());
   for (Node ch : basePane.getChildren()){
     if(ch.getId().equals("#comboBoxAlgo")){
       cb = (ComboBox<String>) ch;
     }
   }

   clickOn(cb).clickOn("Hillclimber");

   verifyThat("Hillclimber", isFocused());

/*    ComboBox<String> cb=null;
   for (Node ch : basePane.getChildren()){
     if(ch.getId().equals("#comboBoxAlgo")){
       cb = (ComboBox<String>) ch;
     }
   }*/

   clickOn("#nextButton");

   try {
     verify(viewmodel).navigation_configureScreen(cb.getValue());
   } catch (NullPointerException e) {
     e.printStackTrace();
   }
 }
}