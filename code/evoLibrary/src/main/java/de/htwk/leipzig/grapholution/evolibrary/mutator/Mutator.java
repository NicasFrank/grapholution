package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Interface fuer Mutationsklassen zur Mutation von Genotypen
 * @param <T> Datentyp der zu mutierenden Genotypen
 */
public interface Mutator<T> {

    /**
     * Funktion zur Mutation eines Genotypen
     * @param genotype Genotyp, der mutiert werden soll
     * @return Neuer mutierter Genotyp
     */
    Genotype<T> mutate (Genotype<T> genotype);

}
