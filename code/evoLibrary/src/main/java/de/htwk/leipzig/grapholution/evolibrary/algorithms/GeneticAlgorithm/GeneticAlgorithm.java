package de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmType;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Noch zu entwickeln
 * @param <T> Datentyp des Genotypen, auf dem der genetische Algorithmus arbeitet
 */
public class GeneticAlgorithm<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;
    private double recombinationChance;
    private Population<T> population;
    private final ArrayList<Population<T>> history;
    private final Selector<T> selector;

    /**
     * Konstruktor ohne Limit
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param population Population des Genotypen
     */
    public GeneticAlgorithm(Mutator<T> mutator, Selector<T> selector, Recombinator<T> recombinator,
            Population<T> population, AlgorithmConfigOptions configOptions) {
        super(population.get(0), configOptions);
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = configOptions.getOrElse("recombinationChance", 1.0);
        this.population = new Population<>(population.createCopy());
        this.selector = selector;
        history = new ArrayList<>();
        history.add(population.createCopy());
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

    @Override
    protected AlgorithmType getType() {
        return AlgorithmType.GeneticAlgorithm;
    }

    @Override
    protected AlgorithmConfigOptions getCustomConfigOptions() {
        var options = new AlgorithmConfigOptions();
        options.add("recombinationChance", recombinationChance);
        return options;
    }

    @Override
    protected void setCustomConfigOptions(AlgorithmConfigOptions options) {
        recombinationChance = options.getOrElse("recombinationChance", 1.0);
    }

    @Override
    public List<Genotype<T>> getHistory() {
        return history.stream().map(Population::getBestIndividuum)
                .collect(Collectors.toList());
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
                recombinator.recombine(population.get(2*i), population.get(2*i + 1));
            }
            mutator.mutate(population.get(2*i));
            mutator.mutate(population.get(2*i + 1));
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