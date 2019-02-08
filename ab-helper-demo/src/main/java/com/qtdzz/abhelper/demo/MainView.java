package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABController;
import com.qtdzz.abhelper.ABExperiment;
import com.qtdzz.abhelper.ABManager;
import com.qtdzz.abhelper.ABType;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {
  static {
    ABExperiment experiment = ABManager.getInstance().createExperiment(
        ABType.THEME, "button", "contrast primary", "contrast",
        "contrast tertiary", "success primary", "success", "success tertiary");
    experiment.addBeforeListener(
        (component, variant) -> LoggerFactory.getLogger(MainView.class)
            .info("===before: {} - {}", experiment.getId(), variant));
    experiment.addAfterListener(
        (component, variant) -> LoggerFactory.getLogger(MainView.class)
            .info("===After: {} - {}", experiment.getId(), variant));
    ABExperiment experiment2 = ABManager.getInstance().createExperiment(
        ABType.TEXT, "button_text", "Register", "Sign Up Now!",
        "Subscribe now!");
    ABExperiment experiment3 = ABManager.getInstance().createExperiment(
        ABType.VALUE, "text_field_value", "", "pre-filled value",
        "pre-filled value2", "pre-filled value3");
  }

  public MainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));

    Button mybutton = new Button("ping ping");
    add(button);
    add(mybutton);

    ABController.applyExperiment(mybutton, "button");

    ABController.applyExperiment(mybutton, "button_text");

    TextField myTextField = new TextField();
    add(myTextField);
    ABController.applyExperiment(myTextField, "text_field_value");
  }
}
