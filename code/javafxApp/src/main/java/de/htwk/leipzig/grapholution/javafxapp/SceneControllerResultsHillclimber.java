package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.StatModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

    public class SceneControllerResultsHillclimber extends SceneController implements Initializable {

    @FXML
    private TextField outputField;
   // @FXML
  //  private TableView<StatModel> tableViewResults;
    @FXML
    private LineChart lineChartResults;
    @FXML
    private TableColumn<Statistics, String> fitness;

    private ViewModel viewModel;

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel, StatModel statModel) {
        this.viewModel = viewModel;
        outputField.textProperty().bind(viewModel.outputFieldProperty());
    }

    public void setTableViewResults() {

        TableView<StatModel> tableViewResults = new TableView<StatModel>();
        final ObservableList<StatModel> data = FXCollections.observableArrayList(
                new StatModel(1));

        TableColumn fitness = new TableColumn("fitness");
        tableViewResults.setItems(data);
        tableViewResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewResults.getColumns().addAll(fitness);

    }
    public void setLineChartResults(Hillclimber hillclimber) {

    }

    public void loadButtonTableView(ActionEvent e) {

    }

    public void loadButtonLineChart(ActionEvent e) {

    }

    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fitness.setCellValueFactory(new PropertyValueFactory<Statistics, String>("fitness"));
    }


    }