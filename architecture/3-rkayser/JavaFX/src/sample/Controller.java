package sample;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class Controller {

  @FXML
  private ImageView settingImage;
  @FXML
  private Pane settings;
  @FXML
  private Rectangle rect1;
  @FXML
  private Rectangle rect2;
  @FXML
  private Rectangle rect3;
  @FXML
  private Rectangle rect4;
  @FXML
  private Rectangle rect5;
  @FXML
  private Rectangle rect6;

  public void rotateImageOnEnter(){
    RotateTransition rt = new RotateTransition(Duration.seconds(0.5),settingImage);
    rt.setByAngle(45);
    rt.play();
  }
  public void settingsVisibility() {
    settings.setVisible(true);
  }
  public void hideSettings(){
    settings.setVisible(false);
  }
  public void colorRectangle1(){
    if(rect1.getFill()==Color.RED){
      rect1.setFill(Color.rgb(0,0,0,0));
    } else {
      rect1.setFill(Color.RED);
    }
  }
  public void colorRectangle2(){
    if(rect2.getFill()==Color.RED){
      rect2.setFill(Color.rgb(0,0,0,0));
    } else {
      rect2.setFill(Color.RED);
    }
  }
  public void colorRectangle3(){
    if(rect3.getFill()==Color.RED){
      rect3.setFill(Color.rgb(0,0,0,0));
    } else {
      rect3.setFill(Color.RED);
    }
  }
  public void colorRectangle4(){
    if(rect4.getFill()==Color.RED) {
      rect4.setFill(Color.rgb(0,0,0,0));
    } else {
      rect4.setFill(Color.RED);
    }
  }
  public void colorRectangle5(){
    if(rect5.getFill()==Color.RED){
      rect5.setFill(Color.rgb(0,0,0,0));
    } else {
      rect5.setFill(Color.RED);
    }
  }
  public void colorRectangle6(){
    if(rect6.getFill()==Color.RED){
      rect6.setFill(Color.rgb(0,0,0,0));
    } else {
      rect6.setFill(Color.RED);
    }
  }
}
