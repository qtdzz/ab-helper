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
    fireBeforeEvent(new ABEvent(event, variant, isEnable()));
    if (variant instanceof Class
        && Component.class.isAssignableFrom((Class) variant)
        && event.getNavigationTarget() != variant) {
      event.rerouteTo((Class<? extends Component>) variant);
    }
    fireAfterEvent(new ABEvent(event, variant, isEnable()));
  }

  protected Object getVariant() {
    if (!isEnable()) {
      return this.getAb()[0];
    }
    return ABStrategy.getInstance().getVariant(this);
  }

}
