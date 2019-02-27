package com.qtdzz.abhelper.demo;

import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;

@Route("mainb/c")
public class BMainView extends HorizontalLayout
    implements BeforeEnterObserver, HasUrlParameter<String> {
  public BMainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));
    add(button);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    LoggerFactory.getLogger(BMainView.class).info("Before enter B");
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    LoggerFactory.getLogger(BMainView.class).info("Parameter in B {}",
        parameter);

  }
}
