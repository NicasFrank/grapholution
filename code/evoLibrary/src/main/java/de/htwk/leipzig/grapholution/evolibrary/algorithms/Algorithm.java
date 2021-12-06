package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmType;

import java.io.*;
import java.util.List;

/**
 * Abstrakte Klasse zur Allgemeinen-Dartstellung eines evolutionaeren Algorithmus
 * @param <T> Datentyp der Genotypen, auf denen der Algorithmus arbeiten soll
 */
public abstract class Algorithm<T> {

    protected final Genotype<T> genotype;
    protected int limit = -1;
    protected int iterations = 0;

    /**
     * Konstruktor fuer einen Algorithmus
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     */
    public Algorithm(Genotype<T> genotype){
        this.genotype = genotype.createCopy();
    }

    /**
     * Konstruktor fuer einen Algorithmus mit Begrenzter Anzahl an Durchlaeufen
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     * @param configOptions Maximale Anzahl an Durchlaeufen, die der Algorithmus durchlaufen soll
     */
    public Algorithm(Genotype<T> genotype, AlgorithmConfigOptions configOptions) {
        this(genotype);
        this.limit = configOptions.getAndConvertOrElse("limit", Integer::parseInt, -1);
    }

    /**
     * Getter fuer Anzahl der Durchlaeufe, die der Algorithmus bereits durchgelaufen ist
     * @return Anzahl der Durchlaeufe, die der Algorithmus bereits durchgelaufen ist
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Funktion zum Ausfuehren des Algorithmus
     * @return Bester Genotyp der am Ende herausgekommen ist
     */
    public abstract Genotype<T> run();

    public void serialize(File file) throws Exception {
        var config = new AlgorithmConfigOptions();
        config.add("limit", Integer.toString(limit));
        config.add("iterations", Integer.toString(iterations));
        config.merge(getCustomConfigOptions());

        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);

        oos.writeObject(getType());
        oos.writeObject(config);

        oos.close();
        fos.close();
    }

    public void deserialize(File file) throws Exception {
        var fis = new FileInputStream(file);
        var ois = new ObjectInputStream(fis);

        var type = (AlgorithmType) ois.readObject();
        if (type != getType()) {
            throw new IllegalArgumentException("");
        }
        var options = (AlgorithmConfigOptions) ois.readObject();

        ois.close();
        fis.close();

        limit = options.getInt("limit");
        iterations = options.getInt("iterations");

        setCustomConfigOptions(options);
    }

    protected abstract AlgorithmType getType();

    protected abstract AlgorithmConfigOptions getCustomConfigOptions();

    protected abstract void setCustomConfigOptions(AlgorithmConfigOptions options);

    public abstract List<Genotype<T>> getHistory();
}
