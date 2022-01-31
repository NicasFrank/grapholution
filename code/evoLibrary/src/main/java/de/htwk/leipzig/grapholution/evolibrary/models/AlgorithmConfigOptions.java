package de.htwk.leipzig.grapholution.evolibrary.models;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasse zur Speicherung von Einstellungen zur Konfiguration eines Algorithmus
 */
public class AlgorithmConfigOptions implements Serializable {
    private final Map<Config, String> options;

    /**
     * Konstruktor
     */
    public AlgorithmConfigOptions() {
        options = new HashMap<>();
    }

    private AlgorithmConfigOptions addAny(Config config, String value) {
        options.put(config, value);
        return this;
    }

    /**
     * Fügt einen ganzzahligen Einstellungswert hinzu
     * @param config Der Identifikator der Einstellung
     * @param value Der neue Wert
     * @return Dieses Objekt mit dem hinzugefügten Wert
     */
    public AlgorithmConfigOptions add(IntConfig config, int value){
        return addAny(config, Integer.toString(value));
    }

    /**
     * Fügt einen kommazahligen Einstellungswert hinzu
     * @param config Der Identifikator der Einstellung
     * @param value Der neue Wert
     * @return Dieses Objekt mit dem hinzugefügten Wert
     */
    public AlgorithmConfigOptions add(DoubleConfig config, double value){
        return addAny(config, Double.toString(value));
    }

    /**
     * Fügt einen boolschen Einstellungswert hinzu
     * @param config Der Identifikator der Einstellung
     * @param value Der neue Wert
     * @return Dieses Objekt mit dem hinzugefügten Wert
     */
    public AlgorithmConfigOptions add(BoolConfig config, boolean value){
        return addAny(config, Boolean.toString(value));
    }

    /**
     * Gibt einen ganzzahligen Einstellungswert zurück
     * @param config Der Identifikator der Einstellung
     * @return Den Wert der Einstellung
     */
    public int getInt(IntConfig config) {
        return Integer.parseInt(options.get(config));
    }

    /**
     * Gibt einen kommazahligen Einstellungswert zurück
     * @param config Der Identifikator der Einstellung
     * @return Den Wert der Einstellung
     */
    public double getDouble(DoubleConfig config) {
        return Double.parseDouble(options.get(config));
    }

    /**
     * Gibt einen boolschen Einstellungswert zurück
     * @param config Der Identifikator der Einstellung
     * @return Den Wert der Einstellung
     */
    public boolean getBool(BoolConfig config) {
        return options.get(config).equals("true");
    }

    /**
     * Gibt einen ganzzahligen Einstellungswert zurück, oder den übergebenen Fallback-Wert, falls kein gespeicherter Wert existiert
     * @param config Der Identifikator der Einstellung
     * @param fallback Der Fallback-Wert, falls kein gespeicherter Wert existiert
     * @return Den Wert der Einstellung oder der Fallback-Wert
     */
    public int getOrElse(IntConfig config, int fallback) {
        try {
            return getInt(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Gibt einen kommazahligen Einstellungswert zurück, oder den übergebenen Fallback-Wert, falls kein gespeicherter Wert existiert
     * @param config Der Identifikator der Einstellung
     * @param fallback Der Fallback-Wert, falls kein gespeicherter Wert existiert
     * @return Den Wert der Einstellung oder der Fallback-Wert
     */
    public double getOrElse(DoubleConfig config, double fallback) {
        try {
            return getDouble(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Gibt einen boolschen Einstellungswert zurück, oder den übergebenen Fallback-Wert, falls kein gespeicherter Wert existiert
     * @param config Der Identifikator der Einstellung
     * @param fallback Der Fallback-Wert, falls kein gespeicherter Wert existiert
     * @return Den Wert der Einstellung oder der Fallback-Wert
     */
    public boolean getOrElse(BoolConfig config, boolean fallback) {
        try {
            return getBool(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Fügt die Einstellungswerte zweier AlgorithmConfigOptions Instanzen zusammen
     * @param other Die Instanzen, mit dem diese Instanz zusammengefügt werden soll
     * @return Dieses Objekt, mit den zusammengefügten Einstellungswerten
     */
    public AlgorithmConfigOptions merge(AlgorithmConfigOptions other) {
        options.putAll(other.options);

        return this;
    }

    /**
     * Speichert die Einstellungswerte dieses Objekts in der übergebenen Datei
     * @param file Die Datei, in dem die Einstellungswerte gespeichert werden sollen
     * @throws IOException Falls ein unbehandelter Fehler beim Speichern der Datei auftritt
     */
    public void serialize(File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }

    /**
     * Lädt die Einstellungswerte aus der übergebenen Datei und fügt sie mit den Einstellungswerten dieses Objektes zusammen
     * @param file Die Datei, aus der die Einstellungswerte geladen werden sollen
     * @return Dieses Objekt, mit den zusammengefügten Einstellungswerten
     * @throws Exception Falls ein unbehandelter Fehler beim Laden der Datei auftritt
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
