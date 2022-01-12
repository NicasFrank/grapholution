package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Rekombination zweier Genotypen nach dem OnePointCrossoverPrinzip
 * @param <T> Datentyp der zu rekombinierenden Genotypen
 */
public class OnePointCrossover<T> implements Recombinator<T>{

    /**
     * Funktion zur Rekombination zweier Genotypen nach dem One-Point-Crossover Prinzip
     * @param individualA Erster Genotyp
     * @param individualB Zweiter Genotyp
     * @return ArrayList mit neuen rekombinierten Genotypen
     */
    @Override
    public List<Genotype<T>> recombine(Genotype<T> individualA, Genotype<T> individualB) {
        var crossoverPoint = ThreadLocalRandom.current().nextInt(individualA.size()-1); //Stelle an der Genotypen getrennt werden

        var newA = individualA.createCopy();
        var newB = individualB.createCopy();

        for(int i = 0; i<=crossoverPoint; i++){
            newA.set(i, individualA.get(i));
            newB.set(i, individualB.get(i));
        }
        for(int i = crossoverPoint+1; i<individualA.size(); i++){
            newA.set(i, individualB.get(i));
            newB.set(i, individualA.get(i));
        }

        return List.of(newA, newB);
    }
}
