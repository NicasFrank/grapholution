package de.htwk.leipzig.grapholution.javafxapp;

public abstract class SceneController {
  private ViewModel viewModel;

  public void setViewModel(ViewModel viewModel) {
      this.viewModel = viewModel;
  }
}
