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

    public AlgorithmConfigOptions add(String name, String value) {
        options.put(name, value);
        return this;
    }

    public AlgorithmConfigOptions add(String name, int value){
        return add(name, Integer.toString(value));
    }

    public AlgorithmConfigOptions add(String name, double value){
        return add(name, Double.toString(value));
    }

    public String get(String name) {
        return options.get(name);
    }

    public int getInt(String name) {
        return Integer.parseInt(options.get(name));
    }

    public double getDouble(String name) {
        return Double.parseDouble(options.get(name));
    }

    public String getOrElse(String name, String fallback) {
        return options.getOrDefault(name, fallback);
    }

    public int getOrElse(String name, int fallback) {
        try {
            return getInt(name);
        } catch (Exception e) {
            return fallback;
        }
    }

    public double getOrElse(String name, double fallback) {
        try {
            return getDouble(name);
        } catch (Exception e) {
            return fallback;
        }
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
}
