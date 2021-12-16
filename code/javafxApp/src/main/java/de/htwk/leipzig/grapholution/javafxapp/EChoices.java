package de.htwk.leipzig.grapholution.javafxapp;

import java.util.ArrayList;
import java.util.List;

public enum EChoices {
  AlgorithmChoice ("AlgorithmChoice"),
  Hillclimber ("Hillclimber"),
  GeneticAlgorithm ("Genetischer Algorithmus"),
  ResultsHillclimber ("ResultsHillclimber"),
  ResultsGeneticAlgorithm ("ResultsGeneticAlgorithm");

  public final String name;

  public static List<EChoices> algorithms() {
    return List.of(EChoices.Hillclimber, EChoices.GeneticAlgorithm);
  }

  public static EChoices getByName(String n) {
    for (var c : values()) {
      if (c.name.equals(n)) {
        return c;
      }
    }

    throw new IllegalArgumentException(String.valueOf(n));
  }

  EChoices(String name){this.name = name;}
}
