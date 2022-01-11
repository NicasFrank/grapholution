package de.htwk.leipzig.grapholution.javafxapp;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.javafxapp.model.TableModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

    public class SCResultsHillclimber extends SceneController implements Initializable {

        @FXML
        TextField outputField;
        @FXML
        TableView<TableModel> tableViewResults;
        @FXML
        LineChart lineChartResults;
        @FXML
        TableColumn<TableModel, String> duration;
        @FXML
        TableColumn<TableModel, String> fitness;

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
            duration.setCellValueFactory((cellData -> cellData.getValue().iterationProperty()));
            fitness.setCellValueFactory((cellData -> cellData.getValue().fitnessIndividualProperty()));
        }
    }