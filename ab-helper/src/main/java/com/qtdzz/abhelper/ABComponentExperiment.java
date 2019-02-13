package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;

public abstract class ABComponentExperiment extends ABExperiment {

  ABComponentExperiment(ABType type, String id, Object... ab) {
    super(type, id, ab);
  }

  public void apply(Component component) {
    validate(component);
    Object variant = getVariant();
    ABEvent abEvent = new ABEvent(component, variant, isEnable());
    fireBeforeEvent(abEvent);
    internalApply(component, variant);
    fireAfterEvent(abEvent);
  }

  private Object getVariant() {
    if (!isEnable()) {
      return this.getAb()[0];
    }
    return ABStrategy.getInstance().getVariant(this);
  }

  protected abstract void internalApply(Component component, Object variant);

  protected abstract void validate(Component component);

}