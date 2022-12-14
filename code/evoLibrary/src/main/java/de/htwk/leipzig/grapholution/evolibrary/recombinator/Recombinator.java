package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.List;

/**
 * Interface fuer Rekombinationsklassen zur Rekombination von 2 Genotypen miteinander
 * @param <T> Datentyp der zu rekombinierenden Genotypen
 */
public interface Recombinator<T> {

    /**
     * Funktion zur Rekombination zweier Genotypen miteinander
     * @param individualA Erster Genotyp
     * @param individualB Zweiter Genotyp
     * @return ArrayList mit neuen rekombinierten Genotypen
     */
    List<Genotype<T>> recombine(Genotype<T> individualA, Genotype<T> individualB);

}
