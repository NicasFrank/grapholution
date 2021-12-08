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

public class SceneControllerResults extends SceneController implements Initializable{

    @FXML
    private TextField outputField;
    @FXML
    private TableColumn<StatModel, String> fitness;
    @FXML
    private TableView<StatModel> tableViewResults;

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

    public void setTableViewResults(SceneControllerResults sceneControllerResults) {

        this.tableViewResults = new TableView<StatModel>();
        final ObservableList<StatModel> data = FXCollections.observableArrayList(
                new StatModel(1));

        TableColumn fitness = new TableColumn("fitness");

        tableViewResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewResults.getColumns().addAll(fitness);
        tableViewResults.setItems(data);
    }

    public void setLineChartResults(Hillclimber hillclimber) {

    }

    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        fitness.setCellValueFactory(new PropertyValueFactory<StatModel, String>("fitness"));
    }


}