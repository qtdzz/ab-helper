package com.qtdzz.abhelper;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABRedirectingViewExperiment extends ABViewExperiment {
  ABRedirectingViewExperiment(String id, Object... ab) {
    super(ABType.REDIRECTING_VIEW, id, ab);
  }

  @Override
  protected void next(BeforeEvent event, Class<? extends Component> variant) {
    event.forwardTo(variant);
  }

  @Override
  protected <T> void next(BeforeEvent event, String nextLocation,
      List<T> nextParams) {
    event.forwardTo(nextLocation, nextParams);
  }
}
