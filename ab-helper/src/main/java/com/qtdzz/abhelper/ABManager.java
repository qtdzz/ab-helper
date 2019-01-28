package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.HasTheme;

public class ABManager {

  public static <T> void setABVariant(Component component,
      ABOptions<T> options) {
    switch (options.getType()) {
    case CLASS:
      setABVariantClass(component, (ABOptions<String>) options);
      break;
    case THEME:
      setABVariantTheme(component, (ABOptions<String>) options);
      break;
    case TEXT:
      setABVariantText(component, (ABOptions<String>) options);
      break;
    default:
      throw new IllegalStateException("Error");
    }
  }

  private static void setABVariantText(Component component,
      ABOptions<String> options) {
    ABStrategy strategy = ABStrategy.getInstance();

    if (!(component instanceof HasText)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasText.class.getName());
      throw new IllegalStateException(message);
    }
    String variant = strategy.getVariant(options);
    ((HasText) component).setText(variant);
  }

  private static void setABVariantClass(Component component,
      ABOptions<String> options) {
    ABStrategy strategy = ABStrategy.getInstance();

    if (!(component instanceof HasStyle)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasStyle.class.getName());
      throw new IllegalStateException(message);
    }
    String variant = strategy.getVariant(options);
    ((HasStyle) component).addClassName(variant);
  }

  private static void setABVariantTheme(Component component,
      ABOptions<String> options) {
    if (!(component instanceof HasTheme)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasTheme.class.getName());
      throw new IllegalStateException(message);
    }
    ABStrategy strategy = ABStrategy.getInstance();
    String variant = strategy.getVariant(options);
    ((HasTheme) component).addThemeName(variant);
  }
}
