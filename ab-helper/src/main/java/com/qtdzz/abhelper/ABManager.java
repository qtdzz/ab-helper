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

  public static ABFactor getFactor(String factorId) {
    return  getDataSource().get(factorId);
  }

  public static ABFactor createFactor(ABFactor factor) {
    ABFactor storedFactor = getFactor(factor.getId());
    if (storedFactor != null) {
      return storedFactor;
    }
    getDataSource().store(factor);
    return factor;
  }

  public static ABFactor createFactor(ABType type, String id,
                                      Object... variants) {
    ABFactor factor = getFactor(id);
    if (factor != null) {
      return factor;
    }
    switch (type) {
    case CLASS:
      factor = new ABClassFactor(id, variants);
      break;
    case TEXT:
      factor = new ABTextFactor(id, variants);
      break;
    case THEME:
      factor = new ABThemeFactor(id, variants);
      break;
    case VALUE:
      factor = new ABValueFactor(id, variants);
      break;
    case VIEW:
      factor = new ABViewFactor(id, variants);
      break;
    case REDIRECTING_VIEW:
      factor = new ABRedirectingViewFactor(id, variants);
      break;
    default:
      throw new IllegalStateException("Not found ABType");
    }
    getDataSource().store(factor);
    return factor;
  }
}
