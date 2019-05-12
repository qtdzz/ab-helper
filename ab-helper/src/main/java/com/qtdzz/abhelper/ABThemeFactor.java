package com.qtdzz.abhelper;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;

public class ABThemeFactor extends ABComponentFactor {
  ABThemeFactor(String id, Object... ab) {
    super(ABType.THEME, id, ab);
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    if (variant == null || StringUtils.isBlank(variant.toString())) {
      return;
    }
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
