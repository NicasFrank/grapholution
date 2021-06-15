package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;

public class OneMaxEvaluator implements Fitnessfun<Boolean> {

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
}
