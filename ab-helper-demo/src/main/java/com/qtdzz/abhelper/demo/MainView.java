package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABView;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout implements ABView {
  @Override
  public String getViewFactorId() {
    return "view_AB";
  }
}
