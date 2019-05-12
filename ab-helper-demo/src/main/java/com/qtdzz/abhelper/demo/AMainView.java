package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABController;
import com.vaadin.flow.router.*;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 * The main view contains a button and a click listener.
 */
@Route("maina")
@StyleSheet("css/demo.css")
public class AMainView extends VerticalLayout
    implements HasUrlParameter<String>, BeforeEnterObserver {
  public AMainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));

    Button mybutton = new Button("ping ping");
    add(button);
    add(mybutton);
    Label demoText = new Label("Demo label");
    add(demoText);
    ABController.applyFactor(mybutton, "button");

    ABController.applyFactor(mybutton, "button_text");
    ABController.applyFactor(demoText, "text_color_class");
    TextField myTextField = new TextField();
    add(myTextField);
    ABController.applyFactor(myTextField, "text_field_value");
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent,
      @OptionalParameter String s) {
    LoggerFactory.getLogger(AMainView.class).info("Parameter from A ==== {}",
        s);

  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    LoggerFactory.getLogger(AMainView.class).info("Before enter A");
  }
}
