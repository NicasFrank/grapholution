package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

public class OneMaxEvaluator implements Fitnessfunction<Boolean> {

    public OneMaxEvaluator(){

    }

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

    @Override
    public int getMaxFitnessValue(Genotype<Boolean> genotype) {
        return genotype.length();
    }
}
