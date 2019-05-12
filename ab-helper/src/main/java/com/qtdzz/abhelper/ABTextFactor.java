package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;

public class ABTextFactor extends ABComponentFactor {
  ABTextFactor(String id, Object... ab) {
    super(ABType.TEXT, id, ab);
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    ((HasText) component).setText((String) variant);
  }

  @Override
  protected void validate(Component component) {
    if (!(component instanceof HasText)) {
      String message = String.format(
          "Can't set %s variants for non %s components", getType(),
          HasText.class.getName());
      throw new IllegalStateException(message);
    }
  }
}
