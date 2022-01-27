package de.htwk.leipzig.grapholution.evolibrary.fitnessfunction;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Klasse zur Evaluierung von Boolean-Genotypen auf Anzahl an false
 */
public class ZeroMaxEvaluator implements FitnessFunction<Boolean> {

    /**
     * Funktion zur Ermittlung von false in einem Boolean-Genotyp
     * @param genotype Genotyp dessen Fitnesswert ermittelt werden soll
     * @return Anzahl der false im Genotyp
     */
    @Override
    public int evaluate(Genotype<Boolean> genotype) {
        var bitSetGenotype = (BitSetGenotype) genotype;

        return bitSetGenotype.zeroCount();
    }

    /**
     * Funktion zur Ermittlung der maximalen Anzahl an false in einem Boolean-Genotypen
     * @param genotype Genotyp dessen maximal moeglicher Fitnesswert ermittelt werden soll
     * @return Maximale Anzahl an false in dieser Art Genotyp
     */
    @Override
    public int getMaxFitnessValue(Genotype<Boolean> genotype) {
        return genotype.size();
    }

    /**
     * Funktion zur Ermittlung des Fitnessziels
     * @return false
     */
    @Override
    public bool target(){
        return false;
    }
}
