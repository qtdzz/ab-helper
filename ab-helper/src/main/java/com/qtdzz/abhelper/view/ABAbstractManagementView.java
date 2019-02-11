package com.qtdzz.abhelper.view;

import com.qtdzz.abhelper.ABDataSource;
import com.qtdzz.abhelper.ABExperiment;
import com.qtdzz.abhelper.ABManager;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

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
    experimentGrid.addColumn(experiment -> experiment.getAb().length)
        .setHeader("No. of variants");
  }

  private ABDataSource getDataSource() {
    return ABManager.getInstance().getDataSource();
  }
}
