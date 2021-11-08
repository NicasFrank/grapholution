package de.htwk.leipzig.grapholution.evolibrary.selectors;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.*;

/**
 * Klasse zur Fitnessproportionalen Selektion von Individuen einer Population
 * @param <T> Datentyp der zu mutierenden Genotypen
 */
public class FitnessproportionalSelection<T> implements Selector<T>{

    private Population<T> population;
    private final List<Genotype<T>> populationList = new ArrayList<>();

    /**
     * Konstruktor der Selector-Klasse
     * @param population, Population aus der selektiert werden soll
     */
    public FitnessproportionalSelection(Population<T> population){
        this.population = population;
        for (int i = 0; i < population.size(); i++) {
            this.populationList.add(this.population.get(i).createCopy());
        }
    }

    /**
     * Selektion mehrerer Individuen mittels der Fitnessproportionalen Selektion
     */
    public void select(){
        Random rand = new Random();
        Set<Genotype<T>> selected = new HashSet<>();

        while(populationList.size() > 0) {
            int selection = getSum() == 0 ? 0 : rand.nextInt(getSum());
            int accFitness = 0;
            int j = 0;
            do {
                accFitness += populationList.get(j).getFitness();
                j++;
            }while(accFitness < selection);

            selected.add(populationList.get(j - 1));
            populationList.remove(populationList.get(j - 1));
        }
        population = new Population<>(selected);
    }

    /**
     * berechnet die Gesamtfitness der population
     * @return fitness
     */
    private int getSum() {
        int sum = 0;
        for (Genotype<T> genotype : populationList) {
            sum += genotype.getFitness();
        }

        return sum;
    }
}