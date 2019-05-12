package com.qtdzz.abhelper;

import java.util.*;

public class ABMemoryDataSource implements ABDataSource {
  private final Map<String, ABFactor> factors = new HashMap<>();
  private final Object lock = new Object();

  @Override
  public void delete(String id) {
    synchronized (lock) {
      factors.remove(id);
    }
  }

  @Override
  public void store(ABFactor factor) {
    synchronized (lock) {
      factors.put(factor.getId(), factor);
    }
  }

  @Override
  public ABFactor get(String id) {
    synchronized (lock) {
      return factors.get(id);
    }
  }

  @Override
  public Collection<ABFactor> getAllFactors() {
    Set<ABFactor> abFactors = new HashSet<>();
    for (Map.Entry<String, ABFactor> stringABFactorEntry : factors
        .entrySet()) {
      abFactors.add(stringABFactorEntry.getValue());
    }
    return abFactors;
  }
}
