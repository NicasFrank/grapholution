package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.Random;

/**
 * Klasse zur Mutation von Boolean-Genotypen nach dem SwitchOneBit Prinzip
 */
public class SwitchOneBit implements Mutator<Boolean> {

    private final Random random = new Random();

    /**
     * Funktion zum switchen eines Einzelnen Bits eines Boolean-Genotypen
     * @param genotype Genotyp, der mutiert werden soll
     */
    @Override
    public void mutate(Genotype<Boolean> genotype) {
        int index = random.nextInt(genotype.length());
        Boolean newBit = !genotype.getValues().get(index);
        genotype.getValues().set(index, newBit);
        genotype.updateFitness(); //Fitness des Genotypen muss nach Veraenderung der Werte neu ermittelt werden
    }

}
