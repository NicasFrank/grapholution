package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

public interface Fitnessfunction<T> {

    int evaluate (Genotype<T> genotype);

    int getMaxFitnessValue(Genotype<T> genotype);

}
