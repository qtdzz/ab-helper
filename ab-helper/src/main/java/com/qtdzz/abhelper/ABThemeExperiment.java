package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;

public class ABThemeExperiment extends ABExperiment {
  ABThemeExperiment(String id, Object... ab) {
    super(ABType.THEME, id, ab);
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    ((HasTheme) component).addThemeName((String) variant);
  }

  @Override
  protected void validate(Component component) {
    if (!(component instanceof HasTheme)) {
      String message = String.format(
          "Can't set %s variants for non %s components", getType(),
          HasTheme.class.getName());
      throw new IllegalStateException(message);
    }
  }
}
