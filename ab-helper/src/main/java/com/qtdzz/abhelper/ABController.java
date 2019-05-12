package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABController {

  private ABController() {
    // no op
  }

  public static void applyFactor(Component component, String factorId) {
    ABFactor factor = ABManager.getFactor(factorId);
    if (factor instanceof ABComponentFactor) {
      ((ABComponentFactor) factor).apply(component);
    } else {
      throw new IllegalStateException(
          String.format("Can't found factor with id '%s'", factorId));
    }
  }

  public static void applyViewFactor(BeforeEvent event, String factorId) {
    ABFactor abViewFactor = ABManager.getFactor(factorId);

    if (!(abViewFactor instanceof ABViewFactor)) {
      return;
    }
    ((ABViewFactor) abViewFactor).apply(event);
  }
}
