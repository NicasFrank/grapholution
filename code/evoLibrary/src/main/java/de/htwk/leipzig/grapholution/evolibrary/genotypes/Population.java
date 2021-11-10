package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasse zur Beschreibung einer Population
 */
public class Population<T> {
    protected List<Genotype<T>> population;
    protected final int size;

    /**
     * Konstruktor zur Erzeugung einer Population mit vorgegebenen größen
     * @param creator funktion zum erstellen Zufälliger Werte des Individuums
     * @param populationSize größe der Population
     * @param genoLength größe eines einzelnen Individuums
     * @param fitnessfunction fitnessfunction der Genotypen
     */
    public Population(Function<Random, T> creator, int populationSize, int genoLength, FitnessFunction<T> fitnessfunction) {
        size = populationSize;
        population = Stream.generate(() -> new Genotype<>(creator, fitnessfunction, genoLength))
                .limit(populationSize)
                .collect(Collectors.toList());
    }

    /**
     * Konstruktor zur Erstellung einer Population aus einer Menge von Genotypen
     * @param genotypes Menge von Genotypen
     */
    public Population(Set<Genotype<T>> genotypes) {
        this.population = new ArrayList<>(genotypes);
        size = genotypes.size();
    }

    /**
     * Konstruktor zur Erstellung einer Population aus einer Liste von Genotypen
     * @param genotypes Liste von Genotypen
     */
    public Population(List<Genotype<T>> genotypes) {
        this.population = genotypes;
        size = genotypes.size();
    }

    /**
     * Funktion zum hinzufügen eines Individuums
     * @param genotype Individuum, welches hinzugefügt wird
     */
    public void add(Genotype<T> genotype) {
        population.add(genotype);
    }

    /**
     * gibt besten Fitnesswert aus Population zurück
     * @return bester Fitnesswert
     */
    public int getBestFitness() {
        return population.stream()
                .mapToInt(Genotype::getFitness)
                .max().orElse(0);
    }

    /**
     * Erstellt eine Kopie der Population
     * @return Liste aller individuen der Population
     */
    public List<Genotype<T>> createCopy(){
        return population.stream()
                .map(Genotype::createCopy)
                .collect(Collectors.toList());
    }


    /**
     * gibt Genotyp aus Population zurück
     * @param i index des Genotyps
     * @return Genotyp
     */
    public Genotype<T> get(int i){
        return population.get(i);
    }

    /**
     * setzt Genotyp an bestimmter Stelle
     * @param i Index
     * @param genotype Genotyp
     */
    public void set(int i, Genotype<T> genotype) {
        population.set(i, genotype.createCopy());
    }

    /**
     * gibt die größe der Population zurück
     * @return größe der Population
     */
    public int size() {
        return population.size();
    }

}