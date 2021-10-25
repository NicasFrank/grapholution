package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

public interface Recombinator<T> {

    void recombine(Genotype<T> individualA, Genotype<T> individualB);

}
