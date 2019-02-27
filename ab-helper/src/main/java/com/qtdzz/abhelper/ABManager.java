package com.qtdzz.abhelper;

public class ABManager {
  private static final ABManager INSTANCE = new ABManager();
  private ABDataSource dataSource;

  private ABManager() {
    // no op
  }

  public static ABManager getInstance() {
    return INSTANCE;
  }

  public void setABDataSource(ABDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public ABDataSource getDataSource() {
    return this.dataSource;
  }

  public ABExperiment getExperiment(String experimentId) {
    return dataSource.get(experimentId);
  }

  public ABExperiment createExperiment(ABType type, String id,
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
    dataSource.store(experiment);
    return experiment;
  }
}
