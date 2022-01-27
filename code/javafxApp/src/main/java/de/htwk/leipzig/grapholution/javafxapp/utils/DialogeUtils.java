package de.htwk.leipzig.grapholution.javafxapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtils {
    public static Optional<ButtonType> ShowDialog(Alert.AlertType type, String title, String content) {
        var alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    public static Optional<ButtonType> ShowAlert(String title, String content) {
        return ShowDialog(Alert.AlertType.ERROR, title, content);
    }

    public static Optional<ButtonType> ShowConfirmation(String title, String content) {
        return ShowDialog(Alert.AlertType.CONFIRMATION, title, content);
    }

    public static Optional<ButtonType> ShowInformation(String title, String content) {
        return ShowDialog(Alert.AlertType.INFORMATION, title, content);
    }

    public static Optional<ButtonType> ShowWarning(String title, String content) {
        return ShowDialog(Alert.AlertType.WARNING, title, content);
    }
}
