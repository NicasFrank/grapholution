package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;

/**
 * Abstrakte Klasse zur Allgemeinen-Dartstellung eines evolutionaeren Algorithmus
 * @param <T> Datentyp der Genotypen, auf denen der Algorithmus arbeiten soll
 */
public abstract class Algorithm<T> {

    protected final Genotype<T> genotype;
    protected int limit = -1;
    protected int iterations = 0;
    protected Statistics<T> statistics = new Statistics<>();

    /**
     * Konstruktor fuer einen Algorithmus
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     */
    public Algorithm(Genotype<T> genotype){
        this.genotype = genotype.createCopy();
    }

    /**
     * Konstruktor fuer einen Algorithmus mit configOptions
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     * @param configOptions Einstellungswerte zur Konfiguration des Algorithmus
     */
    public Algorithm(Genotype<T> genotype, AlgorithmConfigOptions configOptions) {
        this(genotype);
        this.limit = configOptions.getOrElse(IntConfig.Limit, -1);
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

    /**
     * Funktion zum erhalten der Statistiken
     * @return statitics
     */
    public Statistics<T> getStatistics() {
        return statistics.createCopy();
    }
}
