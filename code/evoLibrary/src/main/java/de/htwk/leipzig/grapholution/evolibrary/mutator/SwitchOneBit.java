package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

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
    public Genotype<Boolean> mutate(Genotype<Boolean> genotype) {
        int index = ThreadLocalRandom.current().nextInt(genotype.size());
        genotype.set(index, !genotype.get(index));
        return genotype.createCopy();
    }

}
