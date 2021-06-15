package de.htwk.leipzig.grapholution.evolibrary.fitnessfun;

import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBool;

public class OneMaxEvaluator implements Fitnessfun<GenotypeBool> {

    public OneMaxEvaluator(){

    }

    @Override
    public int evaluate(GenotypeBool genotype) {
        int sum = 0;
        for(int i = 0; i<genotype.lenght(); i++){
            if(genotype.valueAt(i)){
                sum++;
            }
        }
        return sum;
    }
}
