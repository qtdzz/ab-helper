package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABController;
import com.qtdzz.abhelper.ABOptions;

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
    ABOptions<String> options = new ABOptions<>(ABOptions.ABType.THEME,
        "button", "contrast primary", "contrast", "contrast tertiary",
        "success primary", "success", "success tertiary");
    ABController.setABVariant(mybutton, options);
    ABController.setABVariant(mybutton,
        new ABOptions<String>(ABOptions.ABType.TEXT, "button_text", "Register",
            "Sign Up Now!", "Subscribe now!"));

    TextField myTextField = new TextField();
    add(myTextField);
    ABController.setABVariant(myTextField,
        new ABOptions<String>(ABOptions.ABType.VALUE, "text_field_value", "",
            "pre-filled value", "pre-filled value2", "pre-filled value3"));
  }
}
