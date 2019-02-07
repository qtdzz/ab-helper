package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;

public interface ABBeforeListener extends ABEventListener {
  void before(Component component, Object variant);
}
