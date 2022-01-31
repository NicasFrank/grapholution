package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;

/**
 * Basisklasse für die SceneController
 */
public abstract class SceneController {
  protected ViewModel viewModel;

    /**
     * Setzt das viewModel
     * @param viewModel das zu setzende viewModel
     */
  public void setViewModel(ViewModel viewModel) {
      this.viewModel = viewModel;
  }
}
