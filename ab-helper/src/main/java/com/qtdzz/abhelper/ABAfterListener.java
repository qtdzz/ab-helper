package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;

public interface ABAfterListener extends ABEventListener {
  void after(Component component, Object variant);
}
