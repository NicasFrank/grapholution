package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneControllerResultsHillclimber extends SceneController implements Initializable{

    @FXML
    private TextField outputField;
    @FXML
    private TableView<HillModel> tableViewResults;
    @FXML
    private LineChart<HillModel, String> lineChartResults;
    @FXML
    private TableColumn<HillModel, String> fitness;

    private ViewModel viewModel;
    private ViewModelHillclimber viewModelHillclimber;

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        outputField.textProperty().bind(viewModel.outputFieldProperty());
    }

    public void setTableViewResults() {
        final ObservableList<HillModel> data = FXCollections.observableArrayList(
                new HillModel(1),
                new HillModel(4),
                new HillModel(3),
                new HillModel(2));
        fitness.setCellValueFactory(new PropertyValueFactory<>("fitness"));
        tableViewResults.setItems(data);
        tableViewResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void setLineChartResults() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        XYChart.Series series = new XYChart.Series();
        series.setName("Visuals");

        Axis x = new NumberAxis("Dauer", 0, 8, 1);
        Axis y = new NumberAxis("Fitness", 0, 150, 10);

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));

        lineChartResults.getData().add(series);
    }

    public void loadButtonTableView(ActionEvent e) {
    }

    public void loadButtonLineChart(ActionEvent e) {

    }

    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        setTableViewResults();
        setLineChartResults();
    }
}