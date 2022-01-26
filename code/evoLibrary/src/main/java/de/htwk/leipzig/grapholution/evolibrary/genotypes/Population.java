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
     * Konstruktor zur Erzeugung einer Population mit vorgegebenen größen
     * @param creator funktion zum erstellen Zufälliger Werte des Individuums
     * @param populationSize größe der Population
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
     * gibt besten Fitnesswert aus Population zurück
     * @return bester Fitnesswert
     */
    public int getBestFitness() {
        return stream()
                .mapToInt(Genotype::getFitness)
                .max().orElse(0);
    }

    public Genotype<T> getBestIndividual() {
        return stream()
                .max(Comparator.comparing(Genotype<T>::getFitness))
                .orElse(null);
    }

    /**
     * Erstellt eine Kopie der Population
     * @return Liste aller individuen der Population
     */
    public Population<T> createCopy(){
        return new Population<>(stream()
                .map(Genotype::createCopy)
                .collect(Collectors.toList()));
    }
}