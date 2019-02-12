package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;

public class ABEvent {
  private final Component component;
  private final Object selectedVariant;
  private final boolean isEnable;

  ABEvent(Component component, Object selectedVariant, boolean isEnable) {
    this.component = component;
    this.selectedVariant = selectedVariant;
    this.isEnable = isEnable;
  }

  public Component getComponent() {
    return component;
  }

  public Object getSelectedVariant() {
    return selectedVariant;
  }

  public boolean isEnable() {
    return isEnable;
  }
}
