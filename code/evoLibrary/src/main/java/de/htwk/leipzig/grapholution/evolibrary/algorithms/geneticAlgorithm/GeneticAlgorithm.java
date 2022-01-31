package de.htwk.leipzig.grapholution.evolibrary.algorithms.geneticAlgorithm;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.DoubleConfig;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Darstellung eines GenetischenAlgorithmus mit einstellbarem Mutator,
 * Rekombinator und Selektor
 * @param <T> Datentyp des Genotyp auf dem der Algorithmus arbeitet
 */
public class GeneticAlgorithm<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;
    private final double recombinationChance;
    private Population<T> population;
    private final Selector<T> selector;


    /**
     * Konstruktor zur Erstellung eines konfigurierbaren Genetischen Algorithmus
     * @param mutator Mutator den den der Algorithmus verwenden soll
     * @param selector Selektor, mit dem der Algorithmus arbeiten soll
     * @param recombinator Rekombinator den der Algorithmus verwenden soll
     * @param population Population auf der der Algorithmus arbeiten soll
     * @param configOptions Einstellungen fuer Parameter des Algorithmus
     */
    public GeneticAlgorithm(Mutator<T> mutator, Selector<T> selector, Recombinator<T> recombinator,
            Population<T> population, AlgorithmConfigOptions configOptions) {
        super(population.get(0), configOptions);
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = configOptions.getOrElse(DoubleConfig.RecombinationChance, 1.0);
        this.population = population.createCopy();
        this.selector = selector;
        statistics.addToHistory(population);
    }

    /**
     * Der Genetische Algorithmus wir ausgefuehrt, bis das vorgegebene Limit an Iterationen erreicht, oder
     * ein Individuum mit maximalem Fitnesswert erstellt worden ist
     * @return Bestes vom Algorithmus erstelltes Individuum
     */
    public Genotype<T> run(){
        while(hasNotRunToCompletion()) {
            iterate();
        }

        return bestIndividuum();
    }

    /**
     * Ueberpruft ob der Algorithmus das Limit an Iterationen, oder den maximalen Fitnesswert fuer den
     * gegebenen Genotyp erreicht hat
     * @return Bool je nachdem ob wahr oder falsch
     */
    private boolean hasNotRunToCompletion() {
        return (iterations < limit || limit < 0) && !(population.getBestFitness() == genotype.getMaxFitnessValue());
    }

    /**
     * Methode, die einen einzelnen Schritt des Algorithmus ausführt
     * @return Bestes vom Algorithmus erstelltes Individuum
     */
    public Genotype<T> oneStep() {
        if(hasNotRunToCompletion()) {
            iterate();
        }

        return bestIndividuum();
    }

    /**
     * Führt eine Iteration nach dem Prinzip des genetischen Algorithmus aus
     */
    private void iterate(){
        population = selector.select(population);
        statistics.addToHistory(population);

        for(int i = 0; i < population.size() / 2; i++) {
            if(ThreadLocalRandom.current().nextDouble(1) < recombinationChance) {
                var newGenotypes = recombinator.recombine(population.get(2*i), population.get(2*i + 1));
                population.set(2*i, newGenotypes.get(0));
                population.set(2*i+1, newGenotypes.get(1));
            }
            population.set(2*i,mutator.mutate(population.get(2*i)));
            population.set(2*i+1,mutator.mutate(population.get(2*i+1)));
        }
        iterations++;
    }

    /**
     * Gibt das beste Individuum der aktuellsten Population zurück
     * @return Bestes vom Algorithmus erstelltes Individuum
     */
    private Genotype<T> bestIndividuum() {
        return population.stream()
                .max(Comparator.comparingInt(Genotype::getFitness))
                .orElseThrow(() -> new IllegalStateException("The population is empty!"));
    }
}