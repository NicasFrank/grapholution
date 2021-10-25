package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Interface fuer Rekombinationsklassen zur Rekombination von 2 Genotypen miteinander
 * @param <T> Datentyp der zu rekombinierenden Genotypen
 */
public interface Recombinator<T> {

    /**
     * Funktion zur Rekombination zweier Genotypen miteinander
     * @param individualA Erster Genotyp
     * @param individualB Zweiter Genotyp
     */
    void recombine(Genotype<T> individualA, Genotype<T> individualB);

}
