package com.qtdzz.abhelper.view;

import com.qtdzz.abhelper.ABDataSource;
import com.qtdzz.abhelper.ABFactor;
import com.qtdzz.abhelper.ABManager;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public abstract class ABAbstractManagementView extends VerticalLayout {
  protected final Grid<ABFactor> factorGrid;

  public ABAbstractManagementView() {
    factorGrid = new Grid<>();
    initFactorData();
    add(factorGrid);
  }

  protected void initFactorData() {
    factorGrid.setItems(getDataSource().getAllFactors());
    factorGrid.addColumn(ABFactor::getId).setHeader("Factor id");
    factorGrid.addColumn(ABFactor::getType)
        .setHeader("Factor type");
    factorGrid.addColumn(factor -> factor.getVariants().length)
        .setHeader("No. of variants");
    factorGrid.addColumn(new ComponentRenderer<>(abFactor -> {
      Checkbox isEnableCheckBox = new Checkbox(abFactor.isEnable());
      isEnableCheckBox.addValueChangeListener(event -> {
        abFactor.setEnable(event.getValue());
        getDataSource().store(abFactor);
      });
      return isEnableCheckBox;
    })).setHeader("Is enable");

  }

  private ABDataSource getDataSource() {
    return ABManager.getInstance().getDataSource();
  }
}
