package de.htwk.leipzig.grapholution.evolibrary.fitnessfunction;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Klasse zur Evaluierung von Boolean-Genotypen auf Anzahl an trues
 */
public class OneMaxEvaluator implements FitnessFunction<Boolean> {

    /**
     * Funktion zur Ermittlung von trues in einem Boolean-Genotyp
     * @param genotype Genotyp dessen Fitnesswert ermittelt werden soll
     * @return Anzahl der trues im Genotyp
     */
    @Override
    public int evaluate(Genotype<Boolean> genotype) {
        int sum = 0;
        for (var value : genotype.getValues()) {
            if (value) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Funktion zur Ermittlung der maximalen Anzahl an trues in einem Boolean-Genotypen
     * @param genotype Genotyp dessen maximal moeglicher Fitnesswert ermittelt werden soll
     * @return Maximale Anzahl an trues in dieser Art Genotyp
     */
    @Override
    public int getMaxFitnessValue(Genotype<Boolean> genotype) {
        return genotype.size();
    }

    /**
     * Funktion zur Ermittlung des Fitnessziels
     * @return true
     */
    @Override
    public bool target(){
        return true;
    }
}
