package com.qtdzz.abhelper;

public class ABEvent {
  private final Object data;
  private final Object selectedVariant;
  private final boolean isEnable;

  ABEvent(Object data, Object selectedVariant, boolean isEnable) {
    this.data = data;
    this.selectedVariant = selectedVariant;
    this.isEnable = isEnable;
  }

  public Object getComponent() {
    return data;
  }

  public Object getSelectedVariant() {
    return selectedVariant;
  }

  public boolean isEnable() {
    return isEnable;
  }
}
