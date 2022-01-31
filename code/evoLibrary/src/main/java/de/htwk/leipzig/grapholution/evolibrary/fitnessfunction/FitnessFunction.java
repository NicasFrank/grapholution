package de.htwk.leipzig.grapholution.evolibrary.fitnessfunction;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Interface fuer Evaluationsklassen zur Fitnesswertermittlung
 * @param <T> Datentyp der mit der Klasse evaluiert werden kann
 */
public interface FitnessFunction<T> {

    /**
     * Funktion zur Ermittlung des Fitnesswertes eines Genotypen
     * @param genotype Genotyp dessen Fitnesswert ermittelt werden soll
     * @return Ermittelter Fitnesswert
     */
    int evaluate (Genotype<T> genotype);

    /**
     * Funktion, die den maximalen Fitnesswert zurückgibt, den ein Genotyp dieser Art erreichen kann
     * @param genotype Genotyp dessen maximal moeglicher Fitnesswert ermittelt werden soll
     * @return Maximal moeglicher Fitnesswert des Genotypen
     */
    int getMaxFitnessValue(Genotype<T> genotype);

    /**
     * Funktion die die Art des Evaluators zurückgibt
     * @return T Typ des Rückgabe wertes, Wert entspricht dem gewünschten Fitnessziel
     */
    T target();
}
