package de.htwk.leipzig.grapholution.evolibrary.selectors;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse zur Fitnessproportionalen Selektion von Individuen einer Population
 * @param <T> Datentyp der zu mutierenden Genotypen
 */
public class FitnessproportionalSelection<T> implements Selector<T>{
    /**
     * Konstruktor der Selector-Klasse
     */
    public FitnessproportionalSelection(){
    }

    /**
     * Selektion mehrerer Individuen mittels der Fitnessproportionalen Selektion
     * @param population, Population aus der selektiert werden soll
     */
    public Population<T> select(Population<T> population){
       var selected = new Population<T>(new ArrayList<>());

        while (population.size() > 0) {
            int selection = ThreadLocalRandom.current().nextInt(Math.max(1, getSum(population)));
            int accFitness = 0;
            int j = 0;
            do {
                accFitness += population.get(j).getFitness();
                j++;
            } while (accFitness < selection);

            selected.add(population.remove(j - 1));
        }

        return selected;
    }

    /**
     * berechnet die Gesamtfitness der population
     * @return fitness
     */
    private int getSum(Population<T> population) {
        return population.stream()
                .mapToInt(Genotype::getFitness)
                .sum();
    }
}