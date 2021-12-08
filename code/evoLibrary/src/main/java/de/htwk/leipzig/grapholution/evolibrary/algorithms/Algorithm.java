package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;

/**
 * Abstrakte Klasse zur Allgemeinen-Dartstellung eines evolutionaeren Algorithmus
 * @param <T> Datentyp der Genotypen, auf denen der Algorithmus arbeiten soll
 */
public abstract class Algorithm<T> {

    protected final Genotype<T> genotype;
    protected int limit = -1;
    protected int iterations = 0;
    protected Statistics statistics = new Statistics();

    /**
     * Konstruktor fuer einen Algorithmus
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     */
    public Algorithm(Genotype<T> genotype){
        this.genotype = genotype;
    }

    /**
     * Konstruktor fuer einen Algorithmus mit Begrenzter Anzahl an Durchlaeufen
     * @param genotype Genotyp, auf dem der Algorithmus arbeiten soll
     * @param limit Maximale Anzahl an Durchlaeufen, die der Algorithmus durchlaufen soll
     */
    public Algorithm(Genotype<T> genotype, int limit) {
        this(genotype);
        this.limit = limit;
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
    public Statistics getStatistics(){
        return statistics;
    }
}
