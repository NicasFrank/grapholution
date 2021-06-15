package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;
import java.util.Random;

public class GenotypeBoolean extends Genotype<Boolean> {

    public GenotypeBoolean(ArrayList<Boolean> values){
        super(values);
    }

    public GenotypeBoolean(int size){
        super(size);
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
        return new GenotypeBoolean(save);
    }

}
