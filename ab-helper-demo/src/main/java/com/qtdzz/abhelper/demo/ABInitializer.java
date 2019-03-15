package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.*;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ABInitializer implements VaadinServiceInitListener {
  @Override
  public void serviceInit(ServiceInitEvent event) {
    ABMemoryDataSource dataSource = new ABMemoryDataSource();
    ABManager abManager = ABManager
        .initializeABManager(dataSource);

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
    ABExperiment viewAB = abManager.createExperiment(ABType.VIEW, "view_AB",
        BMainView.class, AMainView.class);
    viewAB.addBeforeListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view before"));
    viewAB.addAfterListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view after"));

    ABExperiment redirectingViewExperiment = abManager.createExperiment(
        ABType.REDIRECTING_VIEW, "redirecting_viewAB", BMainView.class,
        AMainView.class);
    redirectingViewExperiment.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting"));
    redirectingViewExperiment.addAfterListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting Done ==== "));
  }
}
