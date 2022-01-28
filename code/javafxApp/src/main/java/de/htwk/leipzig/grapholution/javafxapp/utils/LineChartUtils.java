package de.htwk.leipzig.grapholution.javafxapp.utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LineChartUtils {

    public static void addDataSeries(LineChart<Integer, Integer> chart, List<Integer> data, String name, Color color) {
        var series = new XYChart.Series<Integer, Integer>();
        series.setName(name);

        for (int i = 0; i < data.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, data.get(i)));
        }

        chart.getData().add(series);

        series.nodeProperty().get().setStyle(
                "-fx-stroke-width: 3px;" +
                "-fx-stroke: " + toCssColor(color) + ";"
        );

        chart.setCreateSymbols(false);
    }

    public static void setLegendColors(LineChart<Integer, Integer> chart, List<Color> colors) {
        Platform.runLater(() -> {
            var nodes = new ArrayList<>(chart.lookupAll(".chart-legend-item-symbol"));
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).getStyleClass().stream().anyMatch(s -> s.startsWith("series"))) {
                    var color = toCssColor(colors.get(i));
                    nodes.get(i).setStyle("-fx-background-color: " + color + ", " + color + ";");
                }
            }
        });
    }

    private static String toCssColor(Color color) {
        String rgb = String.format("%d, %d, %d",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));

        return "rgba(" + rgb + ", 1.0)";
    }

}
