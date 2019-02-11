package com.qtdzz.abhelper;

import java.util.Collection;
import java.util.List;

public interface ABDataSource {
  void store(ABExperiment experiment);
  ABExperiment get(String id);

  Collection<ABExperiment> getAllExperiments();
}
