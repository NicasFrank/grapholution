package de.htwk.leipzig.grapholution.javafxapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Hilfsklasse zur Erstellung von Dialogen
 */
public class DialogUtils {
    private static Optional<ButtonType> ShowDialog(Alert.AlertType type, String title, String content) {
        var alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    /**
     * Erstellt einen Error-Dialog mit dem übergebenen Titel und dem übergebenen Inhalt
     * @param title Der Titel des Dialog
     * @param content Der Inhalt des Dialogs
     * @return Der vom Nutzer auf dem Dialog geklickte Button
     */
    public static Optional<ButtonType> ShowAlert(String title, String content) {
        return ShowDialog(Alert.AlertType.ERROR, title, content);
    }

    /**
     * Erstellt einen Bestätigungs-Dialog mit dem übergebenen Titel und dem übergebenen Inhalt
     * @param title Der Titel des Dialog
     * @param content Der Inhalt des Dialogs
     * @return Der vom Nutzer auf dem Dialog geklickte Button
     */
    public static Optional<ButtonType> ShowConfirmation(String title, String content) {
        return ShowDialog(Alert.AlertType.CONFIRMATION, title, content);
    }

    /**
     * Erstellt einen Informations-Dialog mit dem übergebenen Titel und dem übergebenen Inhalt
     * @param title Der Titel des Dialog
     * @param content Der Inhalt des Dialogs
     * @return Der vom Nutzer auf dem Dialog geklickte Button
     */
    public static Optional<ButtonType> ShowInformation(String title, String content) {
        return ShowDialog(Alert.AlertType.INFORMATION, title, content);
    }

    /**
     * Erstellt einen Warnungs-Dialog mit dem übergebenen Titel und dem übergebenen Inhalt
     * @param title Der Titel des Dialog
     * @param content Der Inhalt des Dialogs
     * @return Der vom Nutzer auf dem Dialog geklickte Button
     */
    public static Optional<ButtonType> ShowWarning(String title, String content) {
        return ShowDialog(Alert.AlertType.WARNING, title, content);
    }
}
