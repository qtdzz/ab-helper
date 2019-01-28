package com.qtdzz.abhelper;

import java.util.HashMap;
import java.util.Random;

import com.vaadin.flow.server.VaadinSession;

public class ABStrategy {
  private static final ABStrategy INSTANCE = new ABStrategy();
  private static final String AB_VARIANT_SESSION_ATTRIBUTE = "AB_VARIANT_CHOICE";
  private final Random random = new Random();

  private ABStrategy() {
    // no op
  }

  public static ABStrategy getInstance() {
    return INSTANCE;
  }

  public <T> T getVariant(ABOptions<T> options) {
    HashMap<String, Integer> choiceMap = getChoiceMap();
    Integer choice = choiceMap.get(options.getId());
    if (choice == null) {
      choice = random.nextInt(options.getAb().length);
      choiceMap.put(options.getId(), choice);
    }
    T variant = options.getAb()[choice];
    return variant;
  }

  private HashMap<String, Integer> getChoiceMap() {
    HashMap<String, Integer> choiceMap;
    VaadinSession vaadinSession = VaadinSession.getCurrent();
    vaadinSession.lock();
    try {
      choiceMap = (HashMap<String, Integer>) vaadinSession
          .getAttribute(AB_VARIANT_SESSION_ATTRIBUTE);
    } finally {
      vaadinSession.unlock();
    }
    if (choiceMap == null) {
      choiceMap = new HashMap<>();
      vaadinSession.lock();
      try {
        VaadinSession.getCurrent().setAttribute(AB_VARIANT_SESSION_ATTRIBUTE,
            choiceMap);
      } finally {
        vaadinSession.unlock();
      }
    }
    return choiceMap;
  }
}
