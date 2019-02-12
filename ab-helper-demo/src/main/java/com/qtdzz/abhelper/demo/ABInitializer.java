package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABExperiment;
import com.qtdzz.abhelper.ABManager;
import com.qtdzz.abhelper.ABMemoryDataSource;
import com.qtdzz.abhelper.ABType;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ABInitializer implements VaadinServiceInitListener {
  @Override
  public void serviceInit(ServiceInitEvent event) {
    ABManager abManager = ABManager.getInstance();
    abManager.setABDataSource(new ABMemoryDataSource());
    ABExperiment experiment = abManager.createExperiment(ABType.THEME, "button",
        "contrast primary", "contrast", "contrast tertiary", "success primary",
        "success", "success tertiary");
    experiment.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(ABInitializer.class).info("=== before"));
    experiment.addAfterListener(abEvent -> LoggerFactory
        .getLogger(ABInitializer.class).info("=== after"));
    abManager.createExperiment(ABType.TEXT, "button_text", "Register",
        "Sign Up Now!", "Subscribe now!");
    abManager.createExperiment(ABType.VALUE, "text_field_value", "",
        "pre-filled value", "pre-filled value2", "pre-filled value3");
    abManager.createExperiment(ABType.CLASS, "text_color_class", "green-text", "red-text");
  }
}
