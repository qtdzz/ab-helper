package com.qtdzz.abhelper;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.flow.component.Component;

public abstract class ABExperiment {
  private final ABType type;
  private final Object[] ab;
  private final String id;
  private final Set<ABBeforeListener> beforeListeners = new HashSet<>();
  private final Set<ABAfterListener> afterListeners = new HashSet<>();
  private final Object lock = new Object();

  ABExperiment(ABType type, String id, Object... ab) {
    this.type = type;
    this.id = id;
    this.ab = ab;
  }

  public void addBeforeListener(ABBeforeListener listener) {
    synchronized (lock) {
      beforeListeners.add(listener);
    }
  }

  public void removeBeforeListener(ABBeforeListener listener) {
    synchronized (lock) {
      beforeListeners.remove(listener);
    }
  }

  public void clearListeners() {
    synchronized (lock) {
      beforeListeners.clear();
      afterListeners.clear();
    }
  }

  public void addAfterListener(ABAfterListener listener) {
    synchronized (lock) {
      afterListeners.add(listener);
    }
  }

  public void removeAfterListener(ABAfterListener listener) {
    synchronized (lock) {
      afterListeners.remove(listener);
    }
  }

  public ABType getType() {
    return type;
  }

  public Object[] getAb() {
    return ab;
  }

  public String getId() {
    return id;
  }

  private void fireBeforeEvent(Component component, Object variant) {
    synchronized (lock) {
      for (ABBeforeListener beforeListener : beforeListeners) {
        beforeListener.before(component, variant);
      }
    }
  }

  private void fireAfterEvent(Component component, Object variant) {
    synchronized (lock) {
      for (ABAfterListener afterListener : afterListeners) {
        afterListener.after(component, variant);
      }
    }
  }

  public void apply(Component component) {
    validate(component);
    Object variant = getVariant();
    fireBeforeEvent(component, variant);
    internalApply(component, variant);
    fireAfterEvent(component, variant);
  }

  private Object getVariant() {
    return ABStrategy.getInstance().getVariant(this);
  }

  protected abstract void internalApply(Component component, Object variant);

  protected abstract void validate(Component component);
}
