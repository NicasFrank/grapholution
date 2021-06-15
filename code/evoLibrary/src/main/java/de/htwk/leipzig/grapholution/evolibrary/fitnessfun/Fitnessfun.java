package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;

public interface Fitnessfun<T> {

    int evaluate (Genotype<T> genotype);

}
