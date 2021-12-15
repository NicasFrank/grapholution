package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;

import java.util.Random;

public class ViewModelGeneticAlgorithm{

  private GeneticAlgorithm geneticAlgorithm;
  private BestGenotype bestGenotype;
  private HistoryResults historyResults;
  private EvoLibMapper evoLibMapper;
  private boolean isStepByStep;


  /**
   * erstellt anhand der Konfiguration den gewuenschten genetischen Algorithmus
   * loest erste iteratation des genetischen algorithmus aus
   * @param isStepByStep ob berechnung schritt für schirtt ausfgefuehrt werden soll
   * @param mutatorIsBinary ob die mutation binary ist wenn nicht switch one bit
   * @param mutationChance die wahrscheinlichkeit der binary mutation
   * @param fitnessIsOneMax ob fitnessfunction one max ist sonst zero max
   * @param recombinationChance die wahrscheinlichkeit der rekombination
   * @param populationSize die groesse der population
   * @param genotypeSize die groesse der individuen
   * @param limit die maximale anzahl an generationen
   */
  public ViewModelGeneticAlgorithm(boolean isStepByStep, boolean mutatorIsBinary, double mutationChance,
                                   boolean fitnessIsOneMax, double recombinationChance,double populationSize,
                                   double genotypeSize, double limit){
    this.isStepByStep = isStepByStep;
    Mutator mutator = mutatorIsBinary ? new BinaryMutation((int) mutationChance) : new SwitchOneBit();
    Selector selector= new FitnessproportionalSelection();
    Recombinator recombinator = new OnePointCrossover();
    FitnessFunction fitnessFunction = fitnessIsOneMax ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
    Population population = new Population<>(Random::nextBoolean,(int)populationSize,(int) genotypeSize,fitnessFunction);
    AlgorithmConfigOptions algorithmConfigOptions = new AlgorithmConfigOptions("limit",limit);
    geneticAlgorithm = (limit < 0) ? new GeneticAlgorithm(mutator,selector,recombinator,population)
            : new GeneticAlgorithm(mutator,selector,recombinator,recombinationChance,population,algorithmConfigOptions);
  }

  /**
   * ruft die run methoden des genetischen algorithmus abheangig ob schrittweise oder auf einmal gerechnet werden soll
   * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
   * @return aktuell besten genotypen
   */
  public BestGenotype runAlgorithm(boolean untilDone){
    Genotype currentBestGeno;
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
