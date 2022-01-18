package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Mutation von Boolean-Genotypen nach dem SwitchOneBit Prinzip
 */
public class SwitchOneBit implements Mutator<Boolean> {

    /**
     * Funktion zum switchen eines Einzelnen Bits eines Boolean-Genotypen
     * @param genotype Genotyp, der mutiert werden soll
     * @return Neuer mutierter Genotyp
     */
    @Override
    public Genotype<Boolean> mutate(Genotype<Boolean> genotype) {
        var genotypeCopy = (BitSetGenotype) genotype.createCopy();
        var index = ThreadLocalRandom.current().nextInt(genotypeCopy.size());

        genotypeCopy.flip(index);
        genotypeCopy.updateFitness();
        return genotypeCopy;
    }

}
