package de.htwk.leipzig.grapholution.evolibrary.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmConfigOption implements Serializable {
    private final String name;
    private String value;

    public AlgorithmConfigOption(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public AlgorithmConfigOption(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return getValueOrElse(null);
    }

    public String getValueOrElse(String fallback) {
        return value == null ? fallback : value;
    }


    public static void SerializeAlgorithmConfigs(List<AlgorithmConfigOption> configOptions, File file) throws IOException {
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(configOptions);

        oos.close();
        fos.close();
    }

    public static List<AlgorithmConfigOption> DeserializeAlgorithmConfigs(File file) throws Exception {
        var fos = new FileInputStream(file);
        var ois = new ObjectInputStream(fos);

        var object = ois.readObject();

        ois.close();
        fos.close();

        //noinspection unchecked
        return (ArrayList<AlgorithmConfigOption>) object;
    }
}
