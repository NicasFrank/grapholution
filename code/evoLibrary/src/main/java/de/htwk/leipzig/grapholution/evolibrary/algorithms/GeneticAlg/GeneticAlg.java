package de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlg;

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
public class GeneticAlg<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;
    private final int recombinationChance;
    private Population population;
    private int limit = -1;
    private ArrayList<Population> history;

    /**
     * Konstruktor ohne Limit
     * @param genotype Genotyp auf dem gearbeitet wird
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     * @history enthält Historie der Populationen und fügt die gegebene Population ein
     */
    public GeneticAlg(Genotype<T> genotype, Mutator<T> mutator, Recombinator<T> recombinator, int recombinationChance, Population population) {
        super(genotype);
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = recombinationChance;
        this.population = population;
        history = new ArrayList<Population>();
        history.add(population);
    }

    /**
     * Konstruktor mit Limit
     * @param genotype Genotyp auf dem gearbeitet wird
     * @param mutator enthält Mutation des Genotypen
     * @param recombinator enthält Rekombination zweier Genotypen
     * @param recombinationChance Chance dass Rekombination durchgeführt wird
     * @param population Population des Genotypen
     * @param limit Maximale Anzahl der Iterationen
     * @history enthält Historie der Populationen und fügt die gegebene Population ein
     */
    public GeneticAlg(Genotype<T> genotype, Mutator<T> mutator, Recombinator<T> recombinator, int recombinationChance, Population population, int limit) {
        super(genotype);
        this.mutator = mutator;
        this.recombinator = recombinator;
        this.recombinationChance = recombinationChance;
        this.population = population;
        this.limit = limit;
        history = new ArrayList<Population>();
        history.add(population);
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
        //getNewGeneration().evaluate; //evaluiert die Diversität der Generation für Anschaulichkeit?
        while( (history.size() <= limit || limit < 0) || (population.getBestFitness() < genotype.MAX_FITNESS_VALUE) )
        {
            FitnessproportionalSelection selector = new FitnessproportionalSelection<>(population);
            Population selected = selector.select(population.size());
            population = new Population();

            for(int i = 1; i <= getNewGeneration().size() / 2; i++)
            {
                Random random = new Random();
                double u = random.nextDouble();
                Genotype<T> genoA = selected.get(2*i -1).createCopy();
                Genotype<T> genoB = selected.get(2*i).createCopy();
                if(u < recombinationChance)
                {
                    recombinator.recombine(genoA, genoB);
                }
                mutator.mutate(genoA);
                mutator.mutate(genoB);
                population.add(genoA);
                population.add(genoB);
            }
            history.add(population);
            // getNewGeneration().evaluate;
        }
        return bestIndividuum();
    }

    /**
     * Einfache Get Methode zu @history
     * @return gibt das neueste Element in der Historie zurück
     */
    private Population getNewGeneration() {
        return history.get(history.size() - 1);
    }

    /**
     * Einfache Get Methode zu @history
     * @return gibt das neueste Element in der Historie zurück
     */
    private Population getOldGeneration() {
        return history.get(history.size() - 2);
    }

    private Genotype<T> bestIndividuum() {
        Genotype<T> bestIndividuum = population.get(0);

        for (int i = 0; i < history.size(); i++) {
            for (int j = 0; j < population.size(); j++) {
                if (bestIndividuum.getFitness() < history.get(i).get(j).getFitness()) {
                    bestIndividuum = history.get(i).get(j);
                }
            }
        }

        return bestIndividuum;
    }

}

