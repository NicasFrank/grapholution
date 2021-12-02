package de.htwk.leipzig.grapholution.evolibrary.mutator;

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
     */
    @Override
    public Genotype<Boolean> mutate(Genotype<Boolean> genotype) {
        for(int i = 0; i < genotype.size(); i++){
            if(ThreadLocalRandom.current().nextInt(100)<probability){
                genotype.set(i, !genotype.get(i)); //Bit wird geflippt, sollte Wahrscheinlichkeit eintreten
            }
        }
        return genotype.createCopy();
    }
}
