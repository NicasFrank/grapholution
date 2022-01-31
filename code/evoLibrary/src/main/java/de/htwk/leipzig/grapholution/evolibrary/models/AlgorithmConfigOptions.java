package de.htwk.leipzig.grapholution.evolibrary.models;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasse zur Implementierung und Speicherung von Konfigurationsoptionen fuer Algorithmen in einer Map
 */
public class AlgorithmConfigOptions implements Serializable {
    private final Map<Config, String> options;

    /**
     * Konstruktor fuer Klasse
     */
    public AlgorithmConfigOptions() {
        options = new HashMap<>();
    }

    /**
     * Funktion zum Hinzufuegen einer Konfigurationsoption
     * @param config Option die hinzugefuegt werden soll
     * @param value Wert der Option als String
     * @return Objekt selbst
     */
    private AlgorithmConfigOptions addAny(Config config, String value) {
        options.put(config, value);
        return this;
    }

    /**
     * Funktion zum Hinzufuegen einer Konfigurationsoption die Als int dargestellt wird
     * @param config Option die hinzugefuegt werden soll
     * @param value Wert der Option als Integer
     * @return Objekt selbst
     */
    public AlgorithmConfigOptions add(IntConfig config, int value){
        return addAny(config, Integer.toString(value));
    }

    /**
     * Funktion zum Hinzufuegen einer Konfigurationsoption die Als double dargestellt wird
     * @param config Option die hinzugefuegt werden soll
     * @param value Wert der Option als Integer
     * @return Objekt selbst
     */
    public AlgorithmConfigOptions add(DoubleConfig config, double value){
        return addAny(config, Double.toString(value));
    }

    /**
     * Funktion zum Hinzufuegen einer Konfigurationsoption die Als boolean dargestellt wird
     * @param config Option die hinzugefuegt werden soll
     * @param value Wert der Option als Integer
     * @return Objekt selbst
     */
    public AlgorithmConfigOptions add(BoolConfig config, boolean value){
        return addAny(config, Boolean.toString(value));
    }

    /**
     * Getter fuer eine als int dargestellte Konfiguration
     * @param config Gewuenschte Konfiguration
     * @return Wert der Konfiguration
     */
    public int getInt(IntConfig config) {
        return Integer.parseInt(options.get(config));
    }

    /**
     * Getter fuer eine als double dargestellte Konfiguration
     * @param config Gewuenschte Konfiguration
     * @return Wert der Konfiguration
     */
    public double getDouble(DoubleConfig config) {
        return Double.parseDouble(options.get(config));
    }

    /**
     * Getter fuer eine als boolean dargestellte Konfiguration
     * @param config Gewuenschte Konfiguration
     * @return Wert der Konfiguration
     */
    public boolean getBool(BoolConfig config) {
        return options.get(config).equals("true");
    }

    /**
     * Getter fuer eine als int dargestellte Konfiguration mit Fallback, sollte die Konfiguration nicht gefunden werden
     * @param config Gewuenschte Konfiguration
     * @param fallback Wert auf den zurueckgegriffen wird, sollte die Konfiguration nicht gefunden werden
     * @return Wert der Konfiguration wenn sie gefunden wird, sonst Fallback Wert
     */
    public int getOrElse(IntConfig config, int fallback) {
        try {
            return getInt(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Getter fuer eine als double dargestellte Konfiguration mit Fallback, sollte die Konfiguration nicht gefunden werden
     * @param config Gewuenschte Konfiguration
     * @param fallback Wert auf den zurueckgegriffen wird, sollte die Konfiguration nicht gefunden werden
     * @return Wert der Konfiguration wenn sie gefunden wird, sonst Fallback Wert
     */
    public double getOrElse(DoubleConfig config, double fallback) {
        try {
            return getDouble(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Getter fuer eine als boolean dargestellte Konfiguration mit Fallback, sollte die Konfiguration nicht gefunden werden
     * @param config Gewuenschte Konfiguration
     * @param fallback Wert auf den zurueckgegriffen wird, sollte die Konfiguration nicht gefunden werden
     * @return Wert der Konfiguration wenn sie gefunden wird, sonst Fallback Wert
     */
    public boolean getOrElse(BoolConfig config, boolean fallback) {
        try {
            return getBool(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Funktion zum Mergen zweier AlgorithmConfigOptions Klassen
     * @param other AlgorithmConfigOptions die mit dieser gemerged werden soll
     * @return Verbindung der beiden Klassen
     */
    public AlgorithmConfigOptions merge(AlgorithmConfigOptions other) {
        options.putAll(other.options);

        return this;
    }

    /**
     * Funktion zum Abspeichern einer AlgorithmConfigOptions Klasse in einer Datei
     * @param file Datei in die gespeichert werden soll
     * @throws IOException Fehler wenn Datei nicht gefunden
     */
    public void serialize(File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }

    /**
     * Funktion zum lesen einer AlgorithmConfigOptions aus einer Datei
     * @param file Datei aus der gelesen werden soll
     * @return AlgorithmConfigOptions die gelesen wurde
     * @throws Exception Fehler wenn Datei nicht gefunden
     */
    public AlgorithmConfigOptions deserialize(File file) throws Exception {
        var fos = new FileInputStream(file);
        var ois = new ObjectInputStream(fos);

        var object = ois.readObject();

        ois.close();
        fos.close();

        return merge((AlgorithmConfigOptions) object);
    }
}
