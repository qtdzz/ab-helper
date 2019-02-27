package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABRedirectingViewExperiment extends ABViewExperiment {
  ABRedirectingViewExperiment(String id, Object... ab) {
    super(ABType.REDIRECTING_VIEW, id, ab);
  }

  @Override
  public void apply(BeforeEvent event) {
    Object variant = getVariant();
    fireBeforeEvent(new ABEvent(event, variant, isEnable()));
    if (variant instanceof Class
        && Component.class.isAssignableFrom((Class) variant)
        && event.getNavigationTarget() != variant) {

      event.forwardTo((Class<? extends Component>) variant);
    }
    fireAfterEvent(new ABEvent(event, variant, isEnable()));
  }
}
