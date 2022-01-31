package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasse zur Beschreibung einer Population
 */
public class Population<T> extends ArrayList<Genotype<T>> {

    /**
     * Konstruktor zur Erzeugung einer Population mit vorgegebener Größe
     * @param creator Funktion zum Erstellen zufälliger Werte des Individuums
     * @param populationSize Größe der Population
     */
    public Population(Supplier<Genotype<T>> creator, int populationSize) {
        super(Stream.generate(creator)
                .limit(populationSize)
                .collect(Collectors.toList()));
    }

    /**
     * Konstruktor zur Erstellung einer Population aus einer Menge von Genotypen
     * @param genotypes Menge von Genotypen
     */
    public Population(Collection<Genotype<T>> genotypes) {
        super(genotypes);
    }

    /**
     * Gibt besten Fitnesswert aus Population zurück
     * @return Bester Fitnesswert
     */
    public int getBestFitness() {
        return stream()
                .mapToInt(Genotype::getFitness)
                .max()
                .orElseThrow(() -> new IllegalStateException("The population is empty!"));
    }

    /**
     * Gibt die Güte der Population zurück
     * @return Die Güte
     */
    public double getGoodness() {
        return stream()
            .mapToInt(Genotype::getFitness)
            .average()
            .orElseThrow(() -> new IllegalStateException("The population is empty!"));
    }

    /**
     * Gibt das Individuum mit dem besten Fitnesswert der Population zurück
     * @return Das Individuum mit dem besten Fitnesswert der Population
     */
    public Genotype<T> getBestIndividual() {
        return stream()
                .max(Comparator.comparing(Genotype<T>::getFitness))
                .orElseThrow(() -> new IllegalStateException("The population is empty!"));
    }

    /**
     * Erstellt eine Kopie der Population
     * @return Liste aller Individuen der Population
     */
    public Population<T> createCopy(){
        return new Population<>(stream()
                .map(Genotype::createCopy)
                .collect(Collectors.toList()));
    }
}