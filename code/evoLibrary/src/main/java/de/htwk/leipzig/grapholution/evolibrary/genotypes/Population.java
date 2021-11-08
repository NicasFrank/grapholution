package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import java.util.*;
import java.util.function.Function;

/**
 * Klasse zur Beschreibung einer Population
 */
public class Population<T> {
    protected List<Genotype<T>> population = new ArrayList<>();
    protected final int size;

    /**
     * Konstruktor zur Erzeugung einer Population mit vorgegebenen größen
     * @param populationSize größe der Population
     */
    public Population(Function<Random, T> creator, int populationSize, int genoLength, Fitnessfunction<T> fitnessfunction) {
        size = populationSize;
        for (int i = 0; i < populationSize; i++) {
            population.add(new Genotype<>(creator, fitnessfunction, genoLength));
        }
    }

    /**
     * Konstruktor zur Erstellung einer Population aus einer Menge von Genotypen
     * @param genotypes Menge von Genotypen
     */
    public Population(Set<Genotype<T>> genotypes) {
        size = genotypes.size();
    }

    public Population(List<Genotype<T>> genotypes) {
        this.population = genotypes;
        size = genotypes.size();
    }

    public void add(Genotype<T> genotype) {
        population.add(genotype);
    }

    /**
     * gibt besten Fitnesswert aus Population zurück
     * @return bester Fitnesswert
     */
    public int getBestFitness() {
        Genotype<T> bestIndividuum = population.get(0);
        for (Genotype<T> genotype : population) {
            if (bestIndividuum.getFitness() < genotype.getFitness()) {
                bestIndividuum = genotype;
            }
        }
        return bestIndividuum.getFitness();
    }

    public List<Genotype<T>> createCopy(){
        List<Genotype<T>> copy = new ArrayList<>();
        for(Genotype<T> genome: population) {
            copy.add(genome.createCopy());
        }
        return copy;
    }


    /**
     * gibt Genotyp aus Population zurück
     * @param i index des Genotyps
     * @return Genotyp
     */
    public Genotype<T> get(int i){
        return population.get(i);
    }

    public void set(int i, Genotype<T> genotype) {
        population.set(i, genotype.createCopy());
    }

    /**
     * gibt die größe der Population zurück
     * @return größe der Population
     */
    public int size() {
        return population.size();
    }

}