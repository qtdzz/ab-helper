package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;

public class ABClassFactor extends ABComponentFactor {

  ABClassFactor(String id, Object[] ab) {
    super(ABType.CLASS, id, ab);
  }

  @Override
  public void validate(Component component) {
    if (!(component instanceof HasStyle)) {
      String message = String.format(
          "Can't set %s variants for non %s components", getType(),
          HasStyle.class.getName());
      throw new IllegalStateException(message);
    }
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    ((HasStyle) component).addClassName((String) variant);
  }
}
