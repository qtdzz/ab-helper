package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;

public class ABValueFactor extends ABComponentFactor {

  ABValueFactor(String id, Object... ab) {
    super(ABType.VALUE, id, ab);
  }

  @Override
  protected void internalApply(Component component, Object variant) {
    ((HasValue) component).setValue(variant);
  }

  @Override
  protected void validate(Component component) {
    if (!(component instanceof HasValue)) {
      String message = String.format(
          "Can't set %s variants for non %s components", getType(),
          HasValue.class.getName());
      throw new IllegalStateException(message);
    }
  }
}
