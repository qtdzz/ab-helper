package com.qtdzz.abhelper;

import java.util.Collection;

public interface ABDataSource {
  void delete(String id);

  void store(ABFactor factor);

  ABFactor get(String id);

  Collection<ABFactor> getAllFactors();
}
