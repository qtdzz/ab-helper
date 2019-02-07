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

  public MainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));

    Button mybutton = new Button("ping ping");
    add(button);
    add(mybutton);
    ABExperiment experiment = ABManager.getInstance().createExperiment(
        ABType.THEME, "button", "contrast primary", "contrast",
        "contrast tertiary", "success primary", "success", "success tertiary");
    experiment.addBeforeListener(
        (component, variant) -> LoggerFactory.getLogger(this.getClass())
            .info("===before: {} - {}", experiment.getId(), variant));
    experiment.addAfterListener(
        (component, variant) -> LoggerFactory.getLogger(this.getClass())
            .info("===After: {} - {}", experiment.getId(), variant));
    ABController.applyExperiment(mybutton, experiment.getId());

    ABExperiment experiment2 = ABManager.getInstance().createExperiment(
        ABType.TEXT, "button_text", "Register", "Sign Up Now!",
        "Subscribe now!");
    ABController.applyExperiment(mybutton, experiment2.getId());

    ABExperiment experiment3 = ABManager.getInstance().createExperiment(
        ABType.VALUE, "text_field_value", "", "pre-filled value",
        "pre-filled value2", "pre-filled value3");
    TextField myTextField = new TextField();
    add(myTextField);
    ABController.applyExperiment(myTextField, experiment3.getId());
  }
}
