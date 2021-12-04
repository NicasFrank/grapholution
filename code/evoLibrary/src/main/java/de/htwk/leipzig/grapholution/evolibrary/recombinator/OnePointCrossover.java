package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Rekombination zweier Genotypen nach dem OnePointCrossoverPrinzip
 * @param <T> Datentyp der zu rekombinierenden Genotypen
 */
public class OnePointCrossover<T> implements Recombinator<T>{

    /**
     * Funktion zur Rekombination zweier Genotypen nach dem One-Point-Crossover Prinzip
     * @param individualA Erster Genotyp
     * @param individualB Zweiter Genotyp
     * @return ArrayList mit neuen rekombinierten Genotypen
     */
    @Override
    public ArrayList<Genotype<T>> recombine(Genotype<T> individualA, Genotype<T> individualB) {
        int crossoverPoint = ThreadLocalRandom.current().nextInt(individualA.size()-1); //Stelle an der Genotypen getrennt werden
        ArrayList<T> newA = new ArrayList<>();
        ArrayList<T> newB = new ArrayList<>();
        for(int i = 0; i<=crossoverPoint; i++){
            newA.add(individualA.get(i));
            newB.add(individualB.get(i));
        }
        for(int i = crossoverPoint+1; i<individualA.size(); i++){
            newA.add(individualB.get(i));
            newB.add(individualA.get(i));
        }
        ArrayList<Genotype<T>> recombinedGenotypes = new ArrayList<>();
        recombinedGenotypes.add(new Genotype<>(individualA.getFitnessFunction(), newA));
        recombinedGenotypes.add(new Genotype<>(individualA.getFitnessFunction(), newB));
        return recombinedGenotypes;
    }
}
