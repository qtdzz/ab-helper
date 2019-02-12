package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABController;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@StyleSheet("css/demo.css")
public class MainView extends VerticalLayout {
  public MainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));

    Button mybutton = new Button("ping ping");
    add(button);
    add(mybutton);
    Label demoText = new Label("Demo label");
    add(demoText);
    ABController.applyExperiment(mybutton, "button");

    ABController.applyExperiment(mybutton, "button_text");
    ABController.applyExperiment(demoText, "text_color_class");
    TextField myTextField = new TextField();
    add(myTextField);
    ABController.applyExperiment(myTextField, "text_field_value");
  }
}
