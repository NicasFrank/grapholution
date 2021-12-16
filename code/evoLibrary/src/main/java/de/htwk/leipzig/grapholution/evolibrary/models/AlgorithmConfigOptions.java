package de.htwk.leipzig.grapholution.evolibrary.models;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmConfigOptions implements Serializable {
    private final Map<Config, String> options;

    public AlgorithmConfigOptions() {
        options = new HashMap<>();
    }

    private AlgorithmConfigOptions addAny(Config config, String value) {
        options.put(config, value);
        return this;
    }

    public AlgorithmConfigOptions add(StringConfig config, String value) {
        return addAny(config, value);
    }

    public AlgorithmConfigOptions add(IntConfig config, int value){
        return addAny(config, Integer.toString(value));
    }

    public AlgorithmConfigOptions add(DoubleConfig config, double value){
        return addAny(config, Double.toString(value));
    }

    public AlgorithmConfigOptions add(BoolConfig config, boolean value){
        return addAny(config, Boolean.toString(value));
    }

    public String get(StringConfig config) {
        return options.get(config);
    }

    public int getInt(IntConfig config) {
        return Integer.parseInt(options.get(config));
    }

    public double getDouble(DoubleConfig config) {
        return Double.parseDouble(options.get(config));
    }
    public boolean getBool(BoolConfig config) {
        return Boolean.parseBoolean(options.get(config));
    }

    public String getOrElse(StringConfig config, String fallback) {
        return options.getOrDefault(config, fallback);
    }

    public int getOrElse(IntConfig config, int fallback) {
        try {
            return getInt(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    public double getOrElse(DoubleConfig config, double fallback) {
        try {
            return getDouble(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    public boolean getOrElse(BoolConfig config, boolean fallback) {
        try {
            return getBool(config);
        } catch (Exception e) {
            return fallback;
        }
    }

    public AlgorithmConfigOptions merge(AlgorithmConfigOptions other) {
        options.putAll(other.options);

        return this;
    }

    public void serialize(File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }

    public AlgorithmConfigOptions deserialize(File file) throws Exception {
        var fos = new FileInputStream(file);
        var ois = new ObjectInputStream(fos);

        var object = ois.readObject();

        ois.close();
        fos.close();

        return merge((AlgorithmConfigOptions) object);
    }
}
