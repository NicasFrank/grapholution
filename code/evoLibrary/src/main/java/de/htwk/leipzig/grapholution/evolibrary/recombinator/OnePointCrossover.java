package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasse zur Rekombination zweier Genotypen nach dem OnePointCrossoverPrinzip
 * @param <T> Datentyp der zu rekombinierenden Genotypen
 */
public class OnePointCrossover<T> implements Recombinator<T>{

    /**
     * Funktion Rekombination zweier Genotypen nach dem OnePointCrossoverPrinzip
     * @param individualA Erster Genotyp
     * @param individualB Zweiter Genotyp
     */
    @Override
    public void recombine(Genotype<T> individualA, Genotype<T> individualB) {

        Random random = new Random();
        int crossoverPoint = random.nextInt(individualA.length()-1); //Stelle an der Genotypen getrennt werden
        ArrayList<T> newA = new ArrayList<>();
        ArrayList<T> newB = new ArrayList<>();
        for(int i = 0; i<=crossoverPoint; i++){
            newA.add(individualA.valueAt(i));
            newB.add(individualB.valueAt(i));
        }
        for(int i = crossoverPoint+1; i<individualA.length(); i++){
            newA.add(individualB.valueAt(i));
            newB.add(individualA.valueAt(i));
        }
        individualA.setValues(newA);
        individualB.setValues(newB);
        individualA.updateFitness(); //Fitness des Genotypen muss nach Veraenderung der Werte neu ermittelt werden
        individualB.updateFitness(); //Fitness des Genotypen muss nach Veraenderung der Werte neu ermittelt werden
    }
}
