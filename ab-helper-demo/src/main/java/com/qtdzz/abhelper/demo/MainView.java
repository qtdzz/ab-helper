package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.*;

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

    ABController.applyExperiment(mybutton, "button");

    ABController.applyExperiment(mybutton, "button_text");

    TextField myTextField = new TextField();
    add(myTextField);
    ABController.applyExperiment(myTextField, "text_field_value");
  }
}
