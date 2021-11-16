package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SceneControllerResults extends SceneController{

  @FXML
  private TextField outputField;

  @FXML
  private TableView tableViewResults;

  @FXML
  private LineChart lineChartResults;

  private ViewModel viewModel;
  private Hillclimber hillclimber;

  public void sendButton_back(){
    viewModel.navigation_Back();
  }
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
      List<Genotype<Boolean>> results = hillclimber.getHistory();
      EvoLibMapper evoLibMapper = new EvoLibMapper();
      List<HistoryResults> list = evoLibMapper.map(results);

      HistoryResults hr = new HistoryResults();
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

}
