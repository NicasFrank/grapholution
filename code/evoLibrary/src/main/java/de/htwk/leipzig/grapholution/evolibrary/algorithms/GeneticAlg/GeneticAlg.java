package de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlg;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;

/**
 * Noch zu entwickeln
 * @param <T> Datentyp des Genotypen, auf dem der Gen.Alg. arbeitet
 */
public class GeneticAlg<T> extends Algorithm<T> {

    private final Mutator<T> mutator;
    private final Recombinator<T> recombinator;

    public GeneticAlg(Genotype<T> genotype, Mutator<T> mutator, Recombinator<T> recombinator) {
        super(genotype);
        this.mutator = mutator;
        this.recombinator = recombinator;
    }


}
