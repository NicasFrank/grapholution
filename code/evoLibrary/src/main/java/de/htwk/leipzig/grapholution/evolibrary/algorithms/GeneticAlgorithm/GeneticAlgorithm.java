package de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Noch zu entwickeln
 * @param <T> Datentyp des Genotypen, auf dem der genetische Algorithmus arbeitet
 */
public class GeneticAlgorithm<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;
    private final double recombinationChance;
    private Population<T> population;
    private int limit = -1;
    private final ArrayList<Population<T>> history;
    private final Selector<T> selector;

    /**
     * Konstruktor ohne Limit
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance, dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     */
    public GeneticAlgorithm(Mutator<T> mutator, Selector<T> selector, Recombinator<T> recombinator, double recombinationChance, Population<T> population) {
        super(population.get(0));
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = recombinationChance;
        this.population = new Population<>(population.createCopy());
        this.selector = selector;
        history = new ArrayList<>();
        history.add(population.createCopy());
    }

    /**
     * Konstruktor mit Limit
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance, dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     * @param limit Maximale Anzahl der Iterationen
     */
    public GeneticAlgorithm(Mutator<T> mutator, Selector<T> selector, Recombinator<T> recombinator, double recombinationChance, Population<T> population, int limit) {
        this(mutator, selector, recombinator, recombinationChance, population);
        this.limit = limit;
    }

    /**
     * Ausführung des Gentischen-Algorithmus bis limit an Iterationen erreicht oder maximaler Fitnesswert erreicht selektiert auf der Population neue Generation einer Population entsteht aus paarweise Erzeugung neuer Individuen durch Rekombination, wenn die recombinationChance eintrifft
     * ansonsten werden die Eltern Individuen kopiert
     * dann in jedem Fall mutiert
     *
     * @return bester Genotyp aus der gesamten Historie der Populationen
     */
    public Genotype<T> run(){
        //getNewGeneration().evaluate; //evaluiert die Diversität der Generation für Anschaulichkeit?
        while(hasNotRunToCompletion()) {
            iterate();
        }

        return bestIndividuum();
    }

    private boolean hasNotRunToCompletion() {
        return (history.size() <= limit || limit < 0) && !(population.getBestFitness() == genotype.MAX_FITNESS_VALUE);
    }

    /**
     * Methode, die einen einzelnen Schritt des Algorithmus ausführt
     * @return bestes Individuum
     */
    public Genotype<T> oneStep() {
        if(hasNotRunToCompletion()) {
            iterate();
        }

        return bestIndividuum();
    }

    /**
     * Funktion, die die Population selektiert, rekombiniert und mutiert
     */
    private void iterate(){
        population = selector.select(population);

        for(int i = 0; i < population.size() / 2; i++) {
            if(ThreadLocalRandom.current().nextDouble(1) < recombinationChance) {
                ArrayList<Genotype<T>> newGenotypes = recombinator.recombine(population.get(2*i), population.get(2*i + 1));
                population.set(2*i, newGenotypes.get(0));
                population.set(2*i+1, newGenotypes.get(1));
            }
            population.set(2*i,mutator.mutate(population.get(2*i)));
            population.set(2*i+1,mutator.mutate(population.get(2*i+1)));
        }
        history.add(population.createCopy());
    }

    /**
     * Gibt das beste Individuum der Population zurück
     * @return beste Individuum
     */
    private Genotype<T> bestIndividuum() {
        return history.stream()
                .flatMap(Collection::stream)
                .max(Comparator.comparingInt(Genotype::getFitness))
                .orElse(null);
    }
}