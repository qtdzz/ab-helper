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

    ABComponentFactor factor = (ABComponentFactor) ABManager
        .createFactor(ABType.THEME, "button", "contrast primary",
            "contrast", "contrast tertiary", "success primary", "success",
            "success tertiary");
    factor.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(CustomABApplicationServlet.class).info("=== before"));
    factor.addAfterListener(abEvent -> LoggerFactory
        .getLogger(CustomABApplicationServlet.class).info("=== after"));
    ABManager.createFactor(ABType.TEXT, "button_text", "Register",
        "Sign Up Now!", "Subscribe now!");
    ABManager.createFactor(ABType.VALUE, "text_field_value", "",
        "pre-filled value", "pre-filled value2", "pre-filled value3");
    ABManager.createFactor(ABType.CLASS, "text_color_class", "green-text",
        "red-text");
    ABFactor viewAB = ABManager.createFactor(ABType.VIEW, "view_AB",
        BMainView.class, AMainView.class);
    viewAB.addBeforeListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view before"));
    viewAB.addAfterListener(abEvent -> LoggerFactory.getLogger(this.getClass())
        .info("==== view after"));

    ABFactor redirectingViewFactor = ABManager.createFactor(
        ABType.REDIRECTING_VIEW, "redirecting_viewAB", BMainView.class,
        AMainView.class);
    redirectingViewFactor.addBeforeListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting"));
    redirectingViewFactor.addAfterListener(abEvent -> LoggerFactory
        .getLogger(this.getClass()).info("==== Redirecting Done ==== "));
  }
}
