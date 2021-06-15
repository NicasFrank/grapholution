package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;

public class OneMaxEvaluator implements Fitnessfun<GenotypeBoolean> {

    public OneMaxEvaluator(){

    }

    @Override
    public int evaluate(GenotypeBoolean genotype) {
        int sum = 0;
        for(int i = 0; i<genotype.length(); i++){
            if(genotype.valueAt(i)){
                sum++;
            }
        }
        return sum;
    }
}
