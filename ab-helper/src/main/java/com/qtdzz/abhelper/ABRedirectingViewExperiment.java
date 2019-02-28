package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABRedirectingViewExperiment extends ABViewExperiment {
  ABRedirectingViewExperiment(String id, Object... ab) {
    super(ABType.REDIRECTING_VIEW, id, ab);
  }

  @Override
  protected void internalApply(BeforeEvent event, Object variant) {
    if (variant instanceof Class
        && Component.class.isAssignableFrom((Class) variant)) {
      event.forwardTo((Class<? extends Component>) variant);
    }
  }
}
