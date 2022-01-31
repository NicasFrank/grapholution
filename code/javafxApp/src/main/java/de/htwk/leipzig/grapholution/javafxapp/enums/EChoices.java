package de.htwk.leipzig.grapholution.javafxapp.enums;

import java.util.List;

/**
 * Enum zur Darstellung der in der GUI verfügbar Fenster
 */
public enum EChoices {
  AlgorithmChoice ("AlgorithmChoice"),
  Hillclimber ("Hillclimber"),
  GeneticAlgorithm ("Genetischer Algorithmus"),
  ResultsHillclimber ("ResultsHillclimber"),
  ResultsGeneticAlgorithm ("ResultsGeneticAlgorithm");

  /**
   * Der Name der zugehörigen xml-Datei
   */
  public final String name;

  /**
   * Gibt eine Liste der Enum-Werte zurück, die sich auf Algorithmen beziehen
   * @return Liste der Enum-Werte, die sich auf Algorithmen beziehen
   */
  public static List<EChoices> algorithms() {
    return List.of(EChoices.Hillclimber, EChoices.GeneticAlgorithm);
  }

  /**
   * Gibt den Enum-Wert zurück, dessen Name-Eigenschaft mit dem übergebenen String übereinstimmt
   * @param n Der Name des Enum-Wertes, der zurückgegeben werden soll
   * @return Den Enum-Wert, dessen Name-Eigenschaft mit dem übergebenen String übereinstimmt
   */
  public static EChoices getByName(String n) {
    for (var c : values()) {
      if (c.name.equals(n)) {
        return c;
      }
    }

    throw new IllegalArgumentException(String.valueOf(n));
  }

  /**
   * Konstruktor
   * @param name Der Name den der Enum-Wert haben soll
   */
  EChoices(String name){this.name = name;}
}
