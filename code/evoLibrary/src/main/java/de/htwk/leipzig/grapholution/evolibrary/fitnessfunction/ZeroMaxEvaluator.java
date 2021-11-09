package de.htwk.leipzig.grapholution.evolibrary.fitnessfunction;

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
        int sum = 0;
        for(int i = 0; i<genotype.length(); i++){
            if(!genotype.valueAt(i)){
                sum++;
            }
        }
        return sum;
    }

    /**
     * Funktion zur Ermittlung der maximalen Anzahl an false in einem Boolean-Genotypen
     * @param genotype Genotyp dessen maximal moeglicher Fitnesswert ermittelt werden soll
     * @return Maximale Anzahl an false in dieser Art Genotyp
     */
    @Override
    public int getMaxFitnessValue(Genotype<Boolean> genotype) {
        return genotype.length();
    }

}
