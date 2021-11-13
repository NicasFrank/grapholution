package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.hillclimber.Hillclimber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SceneControllerResults extends SceneController implements Initializable {

  @FXML
  private TextField outputField;

  @FXML
  private TableView tableViewResults;

  @FXML
  private LineChart lineChartResults;

  @FXML
  private TableColumn<Hillclimber,String> colGen;

  @FXML
  private TableColumn<Hillclimber,String> colTime;

  @FXML
  private TableColumn<Hillclimber,String> colFit;

  private ViewModel viewModel;
  private Hillclimber hillclimber;

  /**
   * setter für viewmodel und bindet outputfield an output vom viewmodel
   * @param viewModel gleiche ViewModel für alle
   */
  public void setViewModel(ViewModel viewModel){
      this.viewModel=viewModel;
      outputField.textProperty().bind(viewModel.outputFieldProperty());
  }

  public void setTableViewResults(Hillclimber hillclimber){
      this.hillclimber=hillclimber;
      List<String> results = hillclimber.getHistory();
      /*
      for( int i = 0; i < results.size(); i++)
      {
          System.out.println(results.get(i));
      }

      tableViewResults.getItems();
      */
  }


  public void setLineChartResults(Hillclimber hillclimber){

  }

  public void loadButtonTableView(ActionEvent e){

  }

  public void loadButtonLineChart(ActionEvent e){

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
      colGen.setCellFactory(new PropertyValueFactory("Generation"));
      colTime.setCellFactory(new PropertyValueFactory("Dauer"));
      colFit.setCellFactory(new PropertyValueFactory("beste Fitness"));
  }
}
