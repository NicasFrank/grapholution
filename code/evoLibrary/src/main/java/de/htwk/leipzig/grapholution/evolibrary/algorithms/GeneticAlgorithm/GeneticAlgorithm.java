package de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import java.util.ArrayList;
import java.util.Random;

/**
 * Noch zu entwickeln
 * @param <T> Datentyp des Genotypen, auf dem der Gen.Alg. arbeitet
 */
public class GeneticAlgorithm<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;
    private final double recombinationChance;
    private final Population<T> population;
    private int limit = -1;
    private final ArrayList<Population<T>> history;

    /**
     * Konstruktor ohne Limit
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     */
    public GeneticAlgorithm(Mutator<T> mutator, Recombinator<T> recombinator, double recombinationChance, Population<T> population) {
        super(population.get(0));
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = recombinationChance;
        this.population = population;
        history = new ArrayList<>();
        history.add(new Population<>(population.createCopy()));
    }

    /**
     * Konstruktor mit Limit
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     * @param limit Maximale Anzahl der Iterationen
     */
    public GeneticAlgorithm(Mutator<T> mutator, Recombinator<T> recombinator, double recombinationChance, Population<T> population, int limit) {
        super(population.get(0));
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = recombinationChance;
        this.population = population;
        this.limit = limit;
        history = new ArrayList<>();
        history.add(new Population<>(population.createCopy()));
    }

    /**
     * Ausfuerung des Gentischen Algorithmus
     * Bis limit an Iterationen erreicht oder Maximaler Fitnesswert erreicht
     * Selektiert auf der Population
     * neue Generation einer Population entsteht aus Paarweise erzeugung neuer Individuen
     * durch Rekombination wenn die recombinationChance eintrifft
     * ansonsten werden die Eltern Individuen kopiert
     * dann in jedem Fall mutiert
     *
     * @return bester Genotyp aus der gesamten Historie der Populationen
     */
    public Genotype<T> run(){
        Random random = new Random();
        //getNewGeneration().evaluate; //evaluiert die Diversität der Generation für Anschaulichkeit?
        while( (history.size() <= limit || limit < 0) && !(population.getBestFitness() == genotype.MAX_FITNESS_VALUE) )
        {
            FitnessproportionalSelection<T> selector = new FitnessproportionalSelection<>(population);
            selector.select();

            for(int i = 0; i < population.size() / 2; i++)
            {
                double u = random.nextDouble();
                if(u < recombinationChance)
                {
                    recombinator.recombine(population.get(2*i), population.get(2*i + 1));
                }
                mutator.mutate(population.get(2*i));
                mutator.mutate(population.get(2*i + 1));
            }
            history.add(new Population<>(population.createCopy()));
            // getNewGeneration().evaluate;
        }
        return bestIndividuum();
    }

    /**
     * Einfache Get Methode zu @history
     * @return gibt das neueste Element in der Historie zurück
     */
    private Population<T> getNewGeneration() {
        return history.get(history.size() - 1);
    }

    /**
     * Einfache Get Methode zu @history
     * @return gibt das neueste Element in der Historie zurück
     */
    private Population<T> getOldGeneration() {
        return history.get(history.size() - 2);
    }

    /**
     * Gibt das beste Individuum der Population zurück
     * @return beste Individuum
     */
    private Genotype<T> bestIndividuum() {
        Genotype<T> bestIndividuum = population.get(0);

        for (Population<T> tPopulation : history) {
            for (int j = 0; j < population.size(); j++) {
                if (bestIndividuum.getFitness() < tPopulation.get(j).getFitness()) {
                    bestIndividuum = tPopulation.get(j);
                }
            }
        }
        return bestIndividuum;
    }
}