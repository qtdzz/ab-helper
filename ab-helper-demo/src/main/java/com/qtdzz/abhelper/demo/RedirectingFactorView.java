package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABView;

import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route("re")
public class RedirectingFactorView extends VerticalLayout
    implements HasUrlParameter<String>, ABView {
  @Override
  public String getViewFactorId() {
    return "redirecting_viewAB";
  }

  @Override
  public void setParameter(BeforeEvent event,
      @OptionalParameter String parameter) {
    LoggerFactory.getLogger(RedirectingFactorView.class).info("parent",
        parameter);
  }
}
