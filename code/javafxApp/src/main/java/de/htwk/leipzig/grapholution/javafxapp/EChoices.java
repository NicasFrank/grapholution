package de.htwk.leipzig.grapholution.javafxapp;

public enum EChoices {
  AlgorithmChoice ("AlgorithmChoice"),
  Hillclimber ("Hillclimber"),
  GeneticAlgorithm ("Genetischer Algorithmus"),
  ResultsHillclimber ("ResultsHillclimber"),
  ResultsGeneticAlgorithm ("ResultsGeneticAlgorithm");

  public final String name;

  EChoices(String name){this.name = name;}
}
