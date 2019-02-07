package com.qtdzz.abhelper;

import java.util.HashMap;
import java.util.Map;

public class ABManager {
  private static final ABManager INSTANCE = new ABManager();
  private final Map<String, ABExperiment> experiments = new HashMap<>();

  private ABManager() {
    // no op
  }

  public static ABManager getInstance() {
    return INSTANCE;
  }

  public ABExperiment getExperiment(String experimentId) {
    return experiments.get(experimentId);
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
    default:
      throw new IllegalStateException("Not found ABType");
    }
    experiments.put(id, experiment);
    return experiment;
  }
}
