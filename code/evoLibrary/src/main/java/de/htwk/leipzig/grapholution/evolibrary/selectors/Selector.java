package de.htwk.leipzig.grapholution.evolibrary.selectors;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

/**
 * Interface fuer Mutationsklassen zur Mutation von Genotypen
 * @param <T> Datentyp der zu mutierenden Genotypen
 */
public interface Selector<T> {
    /**
     * Funktion zum Selektieren eines Genotypen
     */
    void select();

}