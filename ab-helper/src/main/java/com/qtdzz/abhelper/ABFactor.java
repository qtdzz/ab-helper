package com.qtdzz.abhelper;

import java.util.HashSet;
import java.util.Set;

public abstract class ABFactor {
  private final ABType type;
  private final Object[] variants;
  private final String id;
  private final Set<ABBeforeListener> beforeListeners = new HashSet<>();
  private final Set<ABAfterListener> afterListeners = new HashSet<>();
  private final Object lock = new Object();
  private boolean isEnable;

  ABFactor(ABType type, String id, Object... variants) {
    this.type = type;
    this.id = id;
    this.variants = variants;
    this.isEnable = true;
  }

  public ABType getType() {
    return type;
  }

  public Object[] getVariants() {
    return variants;
  }

  public String getId() {
    return id;
  }

  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
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

  protected void fireBeforeEvent(ABEvent abEvent) {
    synchronized (lock) {
      if (!abEvent.isEnable()) {
        return;
      }
      for (ABBeforeListener beforeListener : beforeListeners) {
        beforeListener.before(abEvent);
      }
    }
  }

  protected void fireAfterEvent(ABEvent abEvent) {
    synchronized (lock) {
      if (!abEvent.isEnable()) {
        return;
      }
      for (ABAfterListener afterListener : afterListeners) {
        afterListener.after(abEvent);
      }
    }
  }
}
