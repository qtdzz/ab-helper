package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABExperiment;
import com.qtdzz.abhelper.ABManager;
import com.qtdzz.abhelper.ABMemoryDataSource;
import com.qtdzz.abhelper.ABType;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ABInitializer implements VaadinServiceInitListener {
  @Override
  public void serviceInit(ServiceInitEvent event) {
    ABManager.getInstance().setABDataSource(new ABMemoryDataSource());
    ABExperiment experiment = ABManager.getInstance().createExperiment(
        ABType.THEME, "button", "contrast primary", "contrast",
        "contrast tertiary", "success primary", "success", "success tertiary");
    ABExperiment experiment2 = ABManager.getInstance().createExperiment(
        ABType.TEXT, "button_text", "Register", "Sign Up Now!",
        "Subscribe now!");
    ABExperiment experiment3 = ABManager.getInstance().createExperiment(
        ABType.VALUE, "text_field_value", "", "pre-filled value",
        "pre-filled value2", "pre-filled value3");
  }
}
