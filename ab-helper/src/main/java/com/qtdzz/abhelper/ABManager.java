package com.qtdzz.abhelper;

import java.util.Objects;

import org.slf4j.LoggerFactory;

public class ABManager {
  private static ABManager instance;
  private final ABDataSource dataSource;

  private ABManager(ABDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public static ABManager getInstance() {
    Objects.requireNonNull(instance,
        "ABManager hasn't been initialized. Please use ABManage#initializeABManager "
            + "to initialize the manager before using it.");
    return instance;
  }

  public static ABManager initializeABManager(ABDataSource dataSource) {
    if (instance == null) {
      instance = new ABManager(dataSource);
    } else {
      LoggerFactory.getLogger(ABManager.class).warn(
          "The ABManager has been initialized already. Use ABManager#getInstance to get access to the manager.");
    }
    return instance;
  }

  public static ABDataSource getDataSource() {
    return getInstance().dataSource;
  }

  public static ABExperiment getExperiment(String experimentId) {
    return  getDataSource().get(experimentId);
  }

  public static ABExperiment createExperiment(ABExperiment experiment) {
    ABExperiment storedExperiment = getExperiment(experiment.getId());
    if (storedExperiment != null) {
      return storedExperiment;
    }
    getDataSource().store(experiment);
    return experiment;
  }

  public static ABExperiment createExperiment(ABType type, String id,
      Object... variants) {
    ABExperiment experiment = getExperiment(id);
    if (experiment != null) {
      return experiment;
    }
    switch (type) {
    case CLASS:
      experiment = new ABClassExperiment(id, variants);
      break;
    case TEXT:
      experiment = new ABTextExperiment(id, variants);
      break;
    case THEME:
      experiment = new ABThemeExperiment(id, variants);
      break;
    case VALUE:
      experiment = new ABValueExperiment(id, variants);
      break;
    case VIEW:
      experiment = new ABViewExperiment(id, variants);
      break;
    case REDIRECTING_VIEW:
      experiment = new ABRedirectingViewExperiment(id, variants);
      break;
    default:
      throw new IllegalStateException("Not found ABType");
    }
    getDataSource().store(experiment);
    return experiment;
  }
}
