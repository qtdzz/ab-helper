package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABController {

  private ABController() {
    // no op
  }

  public static void applyExperiment(Component component, String experimentId) {
    ABExperiment experiment = ABManager.getInstance()
        .getExperiment(experimentId);
    if (experiment instanceof ABComponentExperiment) {
      ((ABComponentExperiment) experiment).apply(component);
    } else {
      throw new IllegalStateException(
          String.format("Can't found experiment with id '%s'", experimentId));
    }
  }

  public static void applyViewExperiment(BeforeEvent event,
      String experimentId) {
    ABExperiment abViewExperiment = ABManager.getInstance()
        .getExperiment(experimentId);

    if (!(abViewExperiment instanceof ABViewExperiment)) {
      return;
    }
    ((ABViewExperiment) abViewExperiment).apply(event);
  }
}
