package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

/**
 * Klasse zur Evaluierung von Boolean-Genotypen auf Anzahl an trues
 */
public class OneMaxEvaluator implements Fitnessfunction<Boolean> {

    /**
     * Funktion zur Ermittlung von trues in einem Boolean-Genotyp
     * @param genotype Genotyp dessen Fitnesswert ermittelt werden soll
     * @return Anzahl der trues im Genotyp
     */
    @Override
    public int evaluate(Genotype<Boolean> genotype) {
        int sum = 0;
        for(int i = 0; i<genotype.length(); i++){
            if(genotype.valueAt(i)){
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
        return genotype.length();
    }
}
