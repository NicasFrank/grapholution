package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Verwaltung der Populations-Historie und Besten-Individuums-Historie eines Algorithmus, sowie Auswertung dieser
 */
public class Statistics<T> {

    private final List<Population<T>> history = new ArrayList<>();
    private final List<Genotype<T>> bestIndividuals = new ArrayList<>();

    /**
     * Funktion zum Hinzufuegen einer Population des Algorithmus zur Historie, sowie deren bestes Individuum zur
     * Besten-Individuums-Historie
     * @param population Population die hinzugefuegt werden soll
     */
    public void addToHistory(Population<T> population) {
        history.add(population);
        bestIndividuals.add(population.getBestIndividual());
    }

    /**
     * Funktion zum hinzufügen eines besten Individuums zur Besten-Individuums-Historie
     * @param genotype Individuum das hinzugefuegt werden soll
     */
    public void addBestIndividual(Genotype<T> genotype){bestIndividuals.add(genotype);}

    public Statistics<T> createCopy() {
        var c = new Statistics<T>();
        c.history.addAll(history);
        c.bestIndividuals.addAll(bestIndividuals);
        return c;
    }

    /**
     * Gibt die Populations-Historie zurück
     * @return Die Populations-Historie
     */
    public List<Population<T>> getHistory() {
        return new ArrayList<>(history);
    }


    /**
     * Gibt die Besten-Individuums-Historie zurück
     * @return Die Besten-Individuums-Historie
     */
    public List<Genotype<T>> getBestIndividuals() {
        return new ArrayList<>(bestIndividuals);
    }
}
