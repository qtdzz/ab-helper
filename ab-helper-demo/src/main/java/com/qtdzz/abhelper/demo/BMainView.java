package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("mainb")
public class BMainView extends HorizontalLayout implements ABView {
  public BMainView() {
    Button button = new Button("Click me",
        event -> Notification.show("Clicked!"));
    add(button);
  }

  @Override
  public String getViewExperimentId() {
    return "view_AB";
  }

}
