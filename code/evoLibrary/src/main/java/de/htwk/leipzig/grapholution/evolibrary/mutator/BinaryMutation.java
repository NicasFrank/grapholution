package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
     * @return Neuer mutierter Genotyp
     */
    @Override
    public Genotype<Boolean> mutate(Genotype<Boolean> genotype) {
        var genotypeCopy = (BitSetGenotype) genotype.createCopy();

        IntStream.range(0, genotypeCopy.size())
                .filter(i -> ThreadLocalRandom.current().nextInt(100) < probability)
                .forEach(genotypeCopy::flip);

        genotypeCopy.updateFitness();
        return genotypeCopy;
    }
}
