package com.qtdzz.abhelper;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;

public class ABThemeExperiment extends ABExperiment {
  ABThemeExperiment(String id, Object... ab) {
    super(ABType.THEME, id, ab);
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    if (variant == null || StringUtils.isBlank(variant.toString())) {
      return;
    }
    if (component instanceof HasTheme) {
      ((HasTheme) component).addThemeName((String) variant);
    } else {
      String theme = component.getElement().getAttribute("theme");
      if (StringUtils.isBlank(theme)) {
        theme = variant.toString();
      } else {
        theme += " " + variant;
      }
      component.getElement().setAttribute("theme", theme);
    }
  }

  @Override
  protected void validate(Component component) {
    if (!(component instanceof HasTheme) && !(component instanceof HasStyle)) {
      String message = String.format(
          "Can't set %s variants for non %s components", getType(),
          HasTheme.class.getName());
      throw new IllegalStateException(message);
    }
  }
}
