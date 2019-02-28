package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABViewExperiment extends ABExperiment {
  ABViewExperiment(String id, Object... ab) {
    super(ABType.VIEW, id, ab);
  }

  protected ABViewExperiment(ABType type, String id, Object... ab) {
    super(type, id, ab);
  }

  public void apply(BeforeEvent event) {
    Object variant = getVariant();
    verifyBeforeApply(event, variant);
    fireBeforeEvent(new ABEvent(event, variant, isEnable()));
    internalApply(event, variant);
    fireAfterEvent(new ABEvent(event, variant, isEnable()));
  }

  protected void verifyBeforeApply(BeforeEvent event, Object variant) {
    if (event.getNavigationTarget().equals(variant)) {
      String message = String.format(
          "The current view '%s' can't be an option in view variants.",
          event.getNavigationTarget());
      throw new IllegalStateException(message);
    }
  }

  protected Object getVariant() {
    if (!isEnable()) {
      return this.getAb()[0];
    }
    return ABStrategy.getInstance().getVariant(this);
  }

  protected void internalApply(BeforeEvent event, Object variant) {
    if (variant instanceof Class
        && Component.class.isAssignableFrom((Class) variant)) {
      event.rerouteTo((Class<? extends Component>) variant);
    }
  }

}
