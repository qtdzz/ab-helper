package com.qtdzz.abhelper;

import java.util.Collection;

public interface ABDataSource {
  void store(ABExperiment experiment);

  ABExperiment get(String id);

  Collection<ABExperiment> getAllExperiments();
}
