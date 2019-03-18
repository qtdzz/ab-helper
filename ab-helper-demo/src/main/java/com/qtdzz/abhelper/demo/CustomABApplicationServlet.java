package com.qtdzz.abhelper.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.qtdzz.abhelper.*;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
public class CustomABApplicationServlet extends VaadinServlet {
  @Override
  protected void servletInitialized() throws ServletException {
    super.servletInitialized();
    ABMemoryDataSource dataSource = new ABMemoryDataSource();
    ABManager.initializeABManager(dataSource);
    ABStrategy.initialize();

    ABComponentExperiment experiment = (ABComponentExperiment) ABManager
        .createExperiment(ABType.THEME, "button", "contrast primary",
            "contrast", "contrast tertiary", "success primary", "success",
            "success tertiary");
    experiment.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(CustomABApplicationServlet.class).info("=== before"));
    experiment.addAfterListener(abEvent -> LoggerFactory
        .getLogger(CustomABApplicationServlet.class).info("=== after"));
    ABManager.createExperiment(ABType.TEXT, "button_text", "Register",
        "Sign Up Now!", "Subscribe now!");
    ABManager.createExperiment(ABType.VALUE, "text_field_value", "",
        "pre-filled value", "pre-filled value2", "pre-filled value3");
    ABManager.createExperiment(ABType.CLASS, "text_color_class", "green-text",
        "red-text");
    ABExperiment viewAB = ABManager.createExperiment(ABType.VIEW, "view_AB",
        BMainView.class, AMainView.class);
    viewAB.addBeforeListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view before"));
    viewAB.addAfterListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view after"));

    ABExperiment redirectingViewExperiment = ABManager.createExperiment(
        ABType.REDIRECTING_VIEW, "redirecting_viewAB", BMainView.class,
        AMainView.class);
    redirectingViewExperiment.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting"));
    redirectingViewExperiment.addAfterListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting Done ==== "));
  }
}
