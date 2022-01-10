package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;

import java.util.Random;

public class ViewModelGeneticAlgorithm{

  private final GeneticAlgorithm<Boolean> geneticAlgorithm;
  private BestGenotype bestGenotype;
  private HistoryResults historyResults;
  private EvoLibMapper evoLibMapper;
  private boolean isStepByStep;


  /**
   * erstellt anhand der Konfiguration den gewuenschten genetischen Algorithmus
   * loest erste iteratation des genetischen algorithmus aus
   */
  public ViewModelGeneticAlgorithm(AlgorithmConfigOptions options){
    this.isStepByStep = options.getBool(BoolConfig.IsStepByStep);
    var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
    var selector= new FitnessproportionalSelection<Boolean>();
    var recombinator = new OnePointCrossover<Boolean>();
    var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
    var population = new Population<>(Random::nextBoolean,options.getInt(IntConfig.PopulationSize),options.getInt(IntConfig.GenotypeSize),fitnessFunction);
    geneticAlgorithm = new GeneticAlgorithm<>(mutator, selector, recombinator, population, options);
  }

  /**
   * ruft die run methoden des genetischen algorithmus abheangig ob schrittweise oder auf einmal gerechnet werden soll
   * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
   * @return aktuell besten genotypen
   */
  public BestGenotype runAlgorithm(boolean untilDone){
    if(isStepByStep && !untilDone){
      geneticAlgorithm.oneStep();
    } else {
      geneticAlgorithm.run();
    }
    Genotype<Boolean> currentBest =geneticAlgorithm.getStatistics().getBestIndividuals().get(geneticAlgorithm.getStatistics().getBestIndividuals().size()-1);
    System.out.println(currentBest.getFitness()+ " " +currentBest.getAge()  +" Iteration: " + geneticAlgorithm.getIterations());
    return new BestGenotype(currentBest.getFitness(),currentBest.getAge());
  }
}
