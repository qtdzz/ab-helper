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
  private boolean isEnable;

  ABExperiment(ABType type, String id, Object... ab) {
    this.type = type;
    this.id = id;
    this.ab = ab;
    this.isEnable = true;
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

  private void fireBeforeEvent(ABEvent abEvent) {
    synchronized (lock) {
      for (ABBeforeListener beforeListener : beforeListeners) {
        beforeListener.before(abEvent);
      }
    }
  }

  private void fireAfterEvent(ABEvent abEvent) {
    synchronized (lock) {
      for (ABAfterListener afterListener : afterListeners) {
        afterListener.after(abEvent);
      }
    }
  }

  public void apply(Component component) {
    validate(component);
    Object variant = getVariant();
    ABEvent abEvent = new ABEvent(component, variant, this.isEnable);
    fireBeforeEvent(abEvent);
    internalApply(component, variant);
    fireAfterEvent(abEvent);
  }

  private Object getVariant() {
    if (!isEnable) {
      return this.getAb()[0];
    }
    return ABStrategy.getInstance().getVariant(this);
  }

  protected abstract void internalApply(Component component, Object variant);

  protected abstract void validate(Component component);

  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
  }
}
