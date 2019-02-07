package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;

public class ABController {
  private static ABStrategy STRATEGY = ABStrategy.getInstance();

  public static void applyExperiment(Component component, String experimentId) {
    ABExperiment experiment = ABManager.getInstance()
        .getExperiment(experimentId);
    if (experiment != null) {
      experiment.apply(component);
    } else {
      throw new IllegalStateException(
          String.format("Can't found experiment with id '%s'", experimentId));
    }
  }
}
