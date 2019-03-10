package com.qtdzz.abhelper.view;

import com.qtdzz.abhelper.ABDataSource;
import com.qtdzz.abhelper.ABExperiment;
import com.qtdzz.abhelper.ABManager;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public abstract class ABAbstractManagementView extends VerticalLayout {
  protected final Grid<ABExperiment> experimentGrid;

  public ABAbstractManagementView() {
    experimentGrid = new Grid<>();
    initExperimentData();
    add(experimentGrid);
  }

  protected void initExperimentData() {
    experimentGrid.setItems(getDataSource().getAllExperiments());
    experimentGrid.addColumn(ABExperiment::getId).setHeader("Experiment id");
    experimentGrid.addColumn(ABExperiment::getType)
        .setHeader("Experiment type");
    experimentGrid.addColumn(experiment -> experiment.getVariants().length)
        .setHeader("No. of variants");
    experimentGrid.addColumn(new ComponentRenderer<>(experiment -> {
      Checkbox isEnableCheckBox = new Checkbox(experiment.isEnable());
      isEnableCheckBox.addValueChangeListener(event -> {
        experiment.setEnable(event.getValue());
        getDataSource().store(experiment);
      });
      return isEnableCheckBox;
    })).setHeader("Is enable");

  }

  private ABDataSource getDataSource() {
    return ABManager.getInstance().getDataSource();
  }
}
