package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Klasse zur Verwaltung der Populations-Historie und Besten-Individuums-Historie eines Algorithmus, sowie Auswertung dieser
 */
public class Statistics<T> {

    private final List<Population<T>> history = new ArrayList<>();
    private final List<Genotype<T>> bestIndividuals = new ArrayList<>();
    private List<List<Float>> colorHistory = new ArrayList<>();
    /**
     * Funktion zum Hinzufuegen einer Population des Algorithmus zur Historie, sowie deren bestes Individuum zur
     * Besten-Individuums-Historie
     * @param population Population die hinzugefuegt werden soll
     */
    public void addToHistory(Population<T> population) {
        history.add(population);
        bestIndividuals.add(population.getBestIndividual());
        addColorBitString(population);
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
     * Funktion zum erhalt des Proznetanteils der Bits der Stellen des Genotypes die dem Fitnessziel entsprechen einer Population
     * fügt Array mit jeweiligen prozentvorkommen des Fitnessziels an colorHistory an
     */
    public void addColorBitString(Population<T> population){
        var list = population.stream()
                .map(g -> IntStream.range(0, g.size())
                        .filter(i -> g.get(i) == g.getFitnessTarget())
                        .count())
                .map(l -> 1f* l / population.size())
                .collect(Collectors.toList());

        colorHistory.add(list);
    }

    /**
     * getter für Historie des Prozentanteils der Bits einer Population die dem Fitnessziel entsprechen
     * @return colorHistory
     */
    public List<List<Float>> getColorHistory(){
        return new ArrayList<>(colorHistory);
    }
    public List<Population<T>> getHistory() {
        return new ArrayList<>(history);
    }

    public List<Genotype<T>> getBestIndividuals() {
        return new ArrayList<>(bestIndividuals);
    }
}
