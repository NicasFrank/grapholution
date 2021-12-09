package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
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

public class SceneControllerResults extends SceneController implements Initializable{

    @FXML
    private TextField outputField;
    @FXML
    private TableView<HillModel> tableViewResults;
    @FXML
    private LineChart lineChartResults;
    @FXML
    private TableColumn<HillModel, String> fitness;

    private ViewModel viewModel;

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
    public void setLineChartResults(Hillclimber hillclimber) {
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
    }
}