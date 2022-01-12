package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Mutation von Boolean-Genotypen nach dem Binary-Mutation Prinzip
 */
public class BinaryMutation implements Mutator<Boolean> {

    private final int probability;

    /**
     * Konstruktor fuer eine Binary-Mutation Klasse
     * @param probability Mutationswahrscheinlichkeit der einzelnen Booleans beim Durchlauf in Prozent
     */
    public BinaryMutation(int probability){
        this.probability = probability;
    }

    /**
     * Funktion zur Mutation eines Boolean-Genotypen nach dem Binary-Mutation Prinzip
     * @param genotype Genotyp, der mutiert werden soll
     * @return Neuer mutierter Genotyp
     */
    @Override
    public Genotype<Boolean> mutate(Genotype<Boolean> genotype) {
        var genotypeCopy = (BitSetGenotype) genotype.createCopy();

        for(int i = 0; i < genotypeCopy.size(); i++){
            if(ThreadLocalRandom.current().nextInt(100)<probability){
                genotypeCopy.flip(i); //Bit wird geflippt, sollte Wahrscheinlichkeit eintreten
            }
        }

        genotypeCopy.updateFitness();
        return genotypeCopy;
    }
}
