package com.qtdzz.abhelper;

import java.util.*;

public class ABMemoryDataSource implements ABDataSource {
  private final Map<String, ABExperiment> experiments = new HashMap<>();
  private final Object lock = new Object();

  @Override
  public void delete(String id) {
    synchronized (lock) {
      experiments.remove(id);
    }
  }

  @Override
  public void store(ABExperiment experiment) {
    synchronized (lock) {
      experiments.put(experiment.getId(), experiment);
    }
  }

  @Override
  public ABExperiment get(String id) {
    synchronized (lock) {
      return experiments.get(id);
    }
  }

  @Override
  public Collection<ABExperiment> getAllExperiments() {
    Set<ABExperiment> abExperiments = new HashSet<>();
    for (Map.Entry<String, ABExperiment> stringABExperimentEntry : experiments
        .entrySet()) {
      abExperiments.add(stringABExperimentEntry.getValue());
    }
    return abExperiments;
  }
}
