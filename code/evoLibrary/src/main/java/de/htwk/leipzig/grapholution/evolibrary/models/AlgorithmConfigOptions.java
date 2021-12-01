package de.htwk.leipzig.grapholution.evolibrary.models;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AlgorithmConfigOptions implements Serializable {
    private final Map<String, String> options;

    public AlgorithmConfigOptions() {
        options = new HashMap<>();
    }

    public AlgorithmConfigOptions(String name, String value) {
        options = new HashMap<>();
        add(name, value);
    }

    public AlgorithmConfigOptions add(String name, String value) {
        options.put(name, value);
        return this;
    }

    public String get(String name) {
        return options.get(name);
    }

    public String getOrElse(String name, String fallback) {
        return options.getOrDefault(name, fallback);
    }

    public <T> T getAndConvert(String name, Function<String, T> converter) {
        return converter.apply(get(name));
    }

    public <T> T getAndConvertOrElse(String name, Function<String, T> converter, T fallback) {
        var value = get(name);
        return value == null ? fallback : converter.apply(value);
    }

    public AlgorithmConfigOptions merge(AlgorithmConfigOptions other) {
        options.putAll(other.options);

        return this;
    }

    public static void SerializeAlgorithmConfigs(AlgorithmConfigOptions configOptions, File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(configOptions);

        oos.close();
        fos.close();
    }

    public static AlgorithmConfigOptions DeserializeAlgorithmConfigs(File file) throws Exception {
        var fos = new FileInputStream(file);
        var ois = new ObjectInputStream(fos);

        var object = ois.readObject();

        ois.close();
        fos.close();

        return (AlgorithmConfigOptions) object;
    }
}
