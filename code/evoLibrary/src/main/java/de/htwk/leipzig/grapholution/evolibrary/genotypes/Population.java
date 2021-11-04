package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import java.util.*;

/**
 * Klasse zur Beschreibung einer Population
 */
public class Population {
    private List<Genotype> population = new ArrayList<>();

    /**
     * Konstruktor zur Erzeugung einer Population mit vorgegebenen größen
     * @param populationSize größe der Population
     * @param genotypeLength größe der einzelnen Genotypen
     * @param fitnessfunction fitnessfunction zur initialisierung der Genotypen
     */
    public Population(int populationSize, int genotypeLength, Fitnessfunction fitnessfunction) {
        for(int i = 0; i < populationSize; i++) {
            population.add(new Genotype(fitnessfunction, genotypeLength));
        }
    }

    /**
     * Konstruktor zur Erstellung einer Population aus einer Menge von Genotypen
     * @param genotypes Menge von Genotypen
     */
    public Population(Set<Genotype> genotypes) {
        for(Genotype genotype: genotypes) {
            population.add(genotype);
        }
    }

    public Population(){}

    public void add(Genotype genotype) {
        population.add(genotype);
    }

    /**
     * gibt besten Fitnesswert aus Population zurück
     * @return bester Fitnesswert
     */
    public int getBestFitness() {
        Genotype bestIndividuum = population.get(0);
        for (int j = 0; j < population.size(); j++) {
            if (bestIndividuum.getFitness() < population.get(j).getFitness()) {
                bestIndividuum = population.get(j);
            }
        }
        return bestIndividuum.getFitness();
    }


    /**
     * gibt Genotyp aus Population zurück
     * @param i index des Genotyps
     * @return Genotyp
     */
    public Genotype get(int i){
        return population.get(i);
    }

    /**
     * gibt die größe der Population zurück
     * @return größe der Population
     */
    public int size() {
        return population.size();
    }

}
