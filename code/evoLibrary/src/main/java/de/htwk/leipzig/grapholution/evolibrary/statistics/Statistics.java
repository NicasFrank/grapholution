package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Verwaltung der Populations-Historie und Besten-Individuums-Historie eines Algorithmus, sowie Auswertung dieser
 */
public class Statistics {

    private List<Population<?>> history = new ArrayList<>();
    private List<Genotype<?>> bestIndividuals = new ArrayList<>();

    /**
     * Funktion zum hinzufügen eines besten Individuums zur Besten-Individuums-Historie
     * @param genotype Individuum das hinzugefuegt werden soll
     */
    public void addBestIndividual(Genotype<?> genotype){bestIndividuals.add(genotype);}

    /**
     * Funktion zum erhalten der besten Individuen
     * @return List<genotype> beste Individuem
     */
    public List<Genotype<?>> getBestIndividuals(){
        return bestIndividuals;
    }

    /**
     * Funktion zum Hinzufuegen einer Population des Algorithmus zur Historie, sowie deren bestes Individuum zur
     * Besten-Individuums-Historie
     * @param population Population die hinzugefuegt werden soll
     */
    public void addToHistory(Population<?> population) {
        history.add(population);
        bestIndividuals.add(population.getBestIndividual());
    }
    /**
     * Funktion zum erhalten der history
     * @return List<Population<?>> histroy
     */
    public List<Population<?>> getHistory(){
        return history;
    }
}
