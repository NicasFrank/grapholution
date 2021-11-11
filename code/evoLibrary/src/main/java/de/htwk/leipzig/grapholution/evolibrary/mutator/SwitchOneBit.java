package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Mutation von Boolean-Genotypen nach dem SwitchOneBit Prinzip
 */
public class SwitchOneBit implements Mutator<Boolean> {

    /**
     * Funktion zum switchen eines Einzelnen Bits eines Boolean-Genotypen
     * @param genotype Genotyp, der mutiert werden soll
     */
    @Override
    public void mutate(Genotype<Boolean> genotype) {
        int index = ThreadLocalRandom.current().nextInt(genotype.size());
        Boolean newBit = !genotype.get(index);
        genotype.set(index, newBit);
        genotype.updateFitness(); //Fitness des Genotypen muss nach Veraenderung der Werte neu ermittelt werden
    }

}
