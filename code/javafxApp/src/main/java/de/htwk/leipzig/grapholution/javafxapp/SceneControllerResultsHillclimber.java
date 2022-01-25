package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * Scene Kontroller Klasse für Hillclimber Statistik und Visualisierung
 */
public class SceneControllerResultsHillclimber extends SceneController implements Initializable{

    @FXML
    private TextField outputField;
    @FXML
    private TableView<HillModel> tableViewResults;
    @FXML
    private LineChart<HillModel, String> lineChartResults;
    @FXML
    private TableColumn<HillModel, String> fitness;
    /**
     * Objekte der ViewModels
     */
    private ViewModel viewModel;
    private ViewModelHillclimber viewModelHillclimber;

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        outputField.textProperty().bind(viewModel.VMoutputFieldProperty());
    }

    /**
     * Methode um besten Fitnesswert des Hillclimber Algorithmus, in TableView zu setzen
     */

    public void setTableViewResults() {
        final ObservableList<HillModel> data = FXCollections.observableArrayList(
                //Dummy Data
                new HillModel(1),
                new HillModel(4),
                new HillModel(3),
                new HillModel(2));
        fitness.setCellValueFactory(new PropertyValueFactory<>("fitness"));
        tableViewResults.setItems(data);
        tableViewResults.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Methode um zeitlichen Verlauf der Fitnesswerte in LinienChart zu setzen
     */
    public void setLineChartResults() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        XYChart.Series series = new XYChart.Series();
        series.setName("Visuals");

        Axis x = new NumberAxis("Dauer", 0, 8, 1);
        Axis y = new NumberAxis("Fitness", 0, 150, 10);

        //Dummy Data
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

    /**
     * Handled die Rückwärtsnavigation
     */
    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }

    /**
     * Initialisierungsmethode für das Setzen der Visual-Daten
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        setTableViewResults();
        setLineChartResults();
    }
}