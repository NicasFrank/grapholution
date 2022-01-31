package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;

public abstract class SceneController {
  protected ViewModel viewModel;

  public void setViewModel(ViewModel viewModel) {
      this.viewModel = viewModel;
  }
}
