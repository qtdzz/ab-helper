package com.qtdzz.abhelper;

import com.vaadin.flow.component.*;

public class ABController {
  private static ABStrategy STRATEGY = ABStrategy.getInstance();

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
    case VALUE:
      setABVariantValue(component, options);
      break;
    default:
      throw new IllegalStateException("Error");
    }
  }

  private static <T> void setABVariantValue(Component component,
      ABOptions<T> options) {
    if (!(component instanceof HasValue)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasValue.class.getName());
      throw new IllegalStateException(message);
    }
    T variant = STRATEGY.getVariant(options);
    ((HasValue) component).setValue(variant);
  }

  private static void setABVariantText(Component component,
      ABOptions<String> options) {
    if (!(component instanceof HasText)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasText.class.getName());
      throw new IllegalStateException(message);
    }
    String variant = STRATEGY.getVariant(options);
    ((HasText) component).setText(variant);
  }

  private static void setABVariantClass(Component component,
      ABOptions<String> options) {

    if (!(component instanceof HasStyle)) {
      String message = String.format(
          "Can't set %s variants for non %s components", options.getType(),
          HasStyle.class.getName());
      throw new IllegalStateException(message);
    }
    String variant = STRATEGY.getVariant(options);
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
    String variant = STRATEGY.getVariant(options);
    ((HasTheme) component).addThemeName(variant);
  }
}
