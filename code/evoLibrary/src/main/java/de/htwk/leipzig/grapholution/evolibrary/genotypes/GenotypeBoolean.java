package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;
import java.util.Random;

public class GenotypeBoolean extends Genotype<Boolean> {

    public GenotypeBoolean(Fitnessfunction<Boolean> fitnessfunction, ArrayList<Boolean> values){
        super(fitnessfunction, values);
    }

    public GenotypeBoolean(Fitnessfunction<Boolean> fitnessfunction, int size){
        super(fitnessfunction, size);
        Random random = new Random();
        ArrayList<Boolean> save = new ArrayList<>();
        for(int i = 0; i<this.length; i++){
            if(random.nextBoolean()){
                save.add(Boolean.TRUE);
            }
            else {
                save.add(Boolean.FALSE);
            }
        }
        this.values = save;
    }

    public Genotype<Boolean> generateGenotype() {
        Random random = new Random();
        ArrayList<Boolean> save = new ArrayList<>();
        for(int i = 0; i<this.length; i++){
            if(random.nextBoolean()){
                save.add(Boolean.TRUE);
            }
            else {
                save.add(Boolean.FALSE);
            }
        }
        return new GenotypeBoolean(fitnessfunction, save);
    }

}
