package com.qtdzz.abhelper;

public class ABOptions<T> {
  private final ABType type;
  private final T[] ab;
  private final String id;

  public ABOptions(ABType type, String id, T... ab) {
    this.type = type;
    this.id = id;
    this.ab = ab;
  }

  public ABType getType() {
    return type;
  }

  public T[] getAb() {
    return ab;
  }

  public String getId() {
    return id;
  }

  public enum ABType {
    CLASS, THEME, TEXT, VALUE
  }
}
