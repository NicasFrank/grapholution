package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
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

  private GeneticAlgorithm geneticAlgorithm;
  private BestGenotype bestGenotype;
  private EvoLibMapper evoLibMapper;
  private boolean isStepByStep;
  private Genotype genotype;


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
                                   double genotypeSize, double limit, Genotype<Boolean> genotype){
    this.isStepByStep = isStepByStep;
    Mutator mutator;
    Selector selector;
    Recombinator recombinator;
    Population population;
    FitnessFunction fitnessFunction;
    mutator = mutatorIsBinary ? new BinaryMutation((int) mutationChance) : new SwitchOneBit();
    selector = new FitnessproportionalSelection();
    recombinator = new OnePointCrossover();
    fitnessFunction = fitnessIsOneMax ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
    population = new Population<>(Random::nextBoolean,(int)populationSize,(int) genotypeSize,fitnessFunction);
    if(limit < 0){
      geneticAlgorithm = new GeneticAlgorithm(mutator,selector,recombinator,recombinationChance,population);
    } else {
      geneticAlgorithm = new GeneticAlgorithm(mutator,selector,recombinator,recombinationChance,population,(int) limit);
    }
    runAlgorithm(false);
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
