package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
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
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;

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
    var population = new Population<>(
            () -> new BitSetGenotype(Random::nextBoolean, fitnessFunction, options.getInt(IntConfig.GenotypeSize)),
            options.getInt(IntConfig.PopulationSize));
    geneticAlgorithm = new GeneticAlgorithm<>(mutator, selector, recombinator, population, options);
  }

  /**
   * ruft die run methoden des genetischen algorithmus abheangig ob schrittweise oder auf einmal gerechnet werden soll
   * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
   * @return aktuell besten genotypen
   */
  public BestGenotype runAlgorithm(boolean untilDone){
    Genotype<Boolean> currentBestGeno;
    if(isStepByStep && !untilDone){
      currentBestGeno = geneticAlgorithm.oneStep();
    } else {
      currentBestGeno = geneticAlgorithm.run();
    }
    int fitness = currentBestGeno.getFitness();
    int age = currentBestGeno.getAge();
    bestGenotype = new BestGenotype(fitness,age);

    System.out.println(bestGenotype +" Iteration: " + geneticAlgorithm.getIterations());

    return bestGenotype;
  }
}
