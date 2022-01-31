package de.htwk.leipzig.grapholution.javafxapp.handlers;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class LineChartHandler {
    private final LineChart<Integer, Number> chart;
    private List<Color> colors;
    private boolean legendColorSet = false;

    public LineChartHandler(LineChart<Integer, Number> chart, List<String> names, List<Color> colors) {
        this.chart = chart;
        chart.setCreateSymbols(false);
        this.colors = new ArrayList<>(colors);

        for (int i = 0; i < names.size(); i++) {
            var newSeries = new XYChart.Series<Integer, Number>();
            newSeries.setName(names.get(i));

            this.chart.getData().add(newSeries);

            newSeries.nodeProperty().get().setStyle(
                    "-fx-stroke-width: 3px;" +
                            "-fx-stroke: " + toCssColor(colors.get(i)) + ";"
            );
        }

        setLegendColor(chart, colors);
    }

    public void addData(Number value, String name) {
        var series = chart.getData().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst().orElseThrow();

        series.getData().add(new XYChart.Data<>(series.getData().size(), value));

        if (!legendColorSet) {
            setLegendColor(chart, colors);
        }
    }

    private void setLegendColor(LineChart<Integer, Number> chart, List<Color> colors) {
        Platform.runLater(() -> {
            var nodes = new ArrayList<>(chart.lookupAll(".chart-legend-item-symbol"));
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).getStyleClass().stream().anyMatch(s -> s.startsWith("series"))) {
                    var color = toCssColor(colors.get(i));
                    nodes.get(i).setStyle("-fx-background-color: " + color + ", " + color + ";");
                }
            }
            legendColorSet = true;
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
