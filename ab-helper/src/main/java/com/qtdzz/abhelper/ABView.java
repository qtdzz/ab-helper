package com.qtdzz.abhelper;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public interface ABView extends BeforeEnterObserver {

  @Override
  default void beforeEnter(BeforeEnterEvent event) {
    ABController.applyViewExperiment(event, getViewExperimentId());
  }

  String getViewExperimentId();
}
