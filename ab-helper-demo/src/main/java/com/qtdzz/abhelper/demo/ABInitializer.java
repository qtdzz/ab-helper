package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.*;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ABInitializer implements VaadinServiceInitListener {
  @Override
  public void serviceInit(ServiceInitEvent event) {
    ABManager abManager = ABManager.getInstance();
    abManager.setABDataSource(new ABMemoryDataSource());
    ABComponentExperiment experiment = (ABComponentExperiment) abManager
        .createExperiment(ABType.THEME, "button", "contrast primary",
            "contrast", "contrast tertiary", "success primary", "success",
            "success tertiary");
    experiment.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(ABInitializer.class).info("=== before"));
    experiment.addAfterListener(abEvent -> LoggerFactory
        .getLogger(ABInitializer.class).info("=== after"));
    abManager.createExperiment(ABType.TEXT, "button_text", "Register",
        "Sign Up Now!", "Subscribe now!");
    abManager.createExperiment(ABType.VALUE, "text_field_value", "",
        "pre-filled value", "pre-filled value2", "pre-filled value3");
    abManager.createExperiment(ABType.CLASS, "text_color_class", "green-text",
        "red-text");
    ABExperiment view_ab = abManager.createExperiment(ABType.VIEW, "view_AB",
        MainView.class, BMainView.class);
    view_ab.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== view before"));
    view_ab.addAfterListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view after"));

  }
}
