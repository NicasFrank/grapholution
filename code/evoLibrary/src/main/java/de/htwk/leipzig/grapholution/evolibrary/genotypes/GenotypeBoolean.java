package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Genotyp mit Booleans als Werten
 */
public class GenotypeBoolean extends Genotype<Boolean> {

    /**
     * Konstruktor zur Erstellung eines Boolean-Genotypen mit vorgenerierten Werten als List
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param values Werte, die dem Genotyp zugewiesen werden
     */
    public GenotypeBoolean(Fitnessfunction<Boolean> fitnessfunction, List<Boolean> values){
        super(fitnessfunction, values);
    }

    /**
     * Konstruktor zur Erstellung eines Boolean-Genotypen mit zufaellig generierten Werten einer bestimmten Länge
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param size Gewuenschte Laenge des Genotypen
     */
    public GenotypeBoolean(Fitnessfunction<Boolean> fitnessfunction, int size){
        super(fitnessfunction, size);
        Random random = new Random();
        ArrayList<Boolean> save = new ArrayList<>();
        for(int i = 0; i<this.length; i++){
            save.add(random.nextBoolean());
        }
        this.values = save;
    }

    /**
     * Funktion zur Generierung eines neuen Boolean-Genotypen mit selber Laenge und zufaelligen Werten
     * @return Genotyp selber Laenge und zufaelligen Werten
     */
    public Genotype<Boolean> generateGenotype() {
        return new GenotypeBoolean(fitnessfunction, this.length);
    }

}
