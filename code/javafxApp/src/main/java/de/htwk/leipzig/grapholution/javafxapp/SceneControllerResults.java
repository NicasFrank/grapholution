package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public class SceneControllerResults extends SceneController implements Initializable {

        @FXML
        TextField outputField;
        @FXML
        TableView<HistoryResults> tableViewResults;
        @FXML
        LineChart lineChartResults;
        @FXML
        TableColumn<HistoryResults, String> population;
        @FXML
        TableColumn<HistoryResults, String> duration;
        @FXML
        TableColumn<HistoryResults, String> fitness;

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
            duration.setCellValueFactory((cellData -> cellData.getValue().durationProperty()));
            population.setCellValueFactory((cellData -> cellData.getValue().populationProperty()));
            fitness.setCellValueFactory((cellData -> cellData.getValue().fitnessProperty()));
        }
    }