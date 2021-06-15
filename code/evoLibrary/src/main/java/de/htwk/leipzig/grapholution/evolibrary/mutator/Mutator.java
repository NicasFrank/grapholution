package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;

public interface Mutator<T> {

    void mutate (Genotype<T> genotype);

}
