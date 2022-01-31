package de.htwk.leipzig.grapholution.javafxapp.handlers;

import de.htwk.leipzig.grapholution.evolibrary.statistics.ColorBitString;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class CanvasPaintHandler {
    private static final int maxSize = 35;
    private static final Color highColor = Color.BLUE;
    private static final Color lowColor = Color.GREEN;

    public static void paintBitStrings(Canvas canvas, List<ColorBitString> bitStrings) {
        if (bitStrings.size() == 0) {
            return;
        }

        var gc = canvas.getGraphicsContext2D();
        var size = Math.min(canvas.getWidth() / bitStrings.get(0).size(), maxSize);
        canvas.setHeight(size * bitStrings.size());

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font(size / 2));
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        for (int i = 0; i < bitStrings.size(); i++) {
            var bitString = bitStrings.get(i);
            for (int j = 0; j < bitString.size(); j++) {
                var pair = bitString.get(j);

                var color = interpolateColors(pair.getDouble());
                gc.setFill(color);
                gc.fillRect(j * size, i * size, size, size);
                gc.strokeRect(j * size, i * size, size, size);

                gc.setFill(color.getBrightness() > 0.5 ? Color.BLACK : Color.WHITE);
                gc.fillText(
                        pair.getBit() ? "1" : "0",
                        Math.round(j * size + size / 2),
                        Math.round(i * size + size / 2)
                );
            }
        }
    }

    private static Color interpolateColors(double t) {
        return Color.hsb(
                interpolate(t, lowColor.getHue(), highColor.getHue()),
                interpolate(t, lowColor.getSaturation(), highColor.getSaturation()),
                interpolate((1 - Math.abs(0.5 - t) * 2) * 0.7 + 0.15, 0, 1)
        );
    }

    private static double interpolate(double t, double v1, double v2) {
        return (v2 - v1) * t + v1;
    }
}
