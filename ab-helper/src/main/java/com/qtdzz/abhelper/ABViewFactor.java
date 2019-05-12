package com.qtdzz.abhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.BeforeEvent;

public class ABViewFactor extends ABFactor {
  ABViewFactor(String id, Object... ab) {
    super(ABType.VIEW, id, ab);
  }

  protected ABViewFactor(ABType type, String id, Object... ab) {
    super(type, id, ab);
  }

  public void apply(BeforeEvent event) {
    Object variant = getVariant();
    validate(event, variant);
    fireBeforeEvent(new ABEvent(event, variant, isEnable()));
    internalApply(event, variant);
    fireAfterEvent(new ABEvent(event, variant, isEnable()));
  }

  protected void validate(BeforeEvent event, Object variant) {
    if (event.getNavigationTarget().equals(variant)) {
      String message = String.format(
          "The current view '%s' can't be an option in view variants.",
          event.getNavigationTarget());
      throw new IllegalStateException(message);
    }
    if (!(variant instanceof Class)
            || !Component.class.isAssignableFrom((Class) variant)) {
      throw new IllegalStateException("Variant is invalid");
    }
  }

  protected Object getVariant() {
    if (!isEnable()) {
      return this.getVariants()[0];
    }
    return ABStrategy.getInstance().getVariant(this);
  }

  protected void internalApply(BeforeEvent event, Object variant) {
    Optional<String> currentBaseUrl = event.getSource().getRegistry()
        .getTargetUrl((Class<? extends Component>) event.getNavigationTarget());
    Optional<String> nextTargetUrl = event.getSource().getRegistry()
        .getTargetUrl((Class<? extends Component>) variant);
    List<String> segments = event.getLocation().getSegments();
    if (!currentBaseUrl.isPresent() || !nextTargetUrl.isPresent()) {
      throw new IllegalStateException("Target or next target is empty");
    }
    ArrayList<String> strings = getCurrentBaseSegments(currentBaseUrl.get());
    List<String> nextSegments = segments.stream()
        .filter(s1 -> !strings.remove(s1)).collect(Collectors.toList());
    String nextLocation = getNextLocation(nextTargetUrl.get());
    if (nextSegments.isEmpty()) {
      next(event, (Class<? extends Component>) variant);
    } else {
      next(event, nextLocation, nextSegments);
    }
  }

  private String getNextLocation(String nextTargetUrl) {
    String nextLocation = StringUtils.substringBefore(nextTargetUrl, "{");
    return StringUtils.removeEnd(nextLocation, "/");
  }

  private ArrayList<String> getCurrentBaseSegments(String currentBaseUrl) {
    String[] split = StringUtils.split(currentBaseUrl, "/");
    ArrayList<String> strings = new ArrayList<>();
    Collections.addAll(strings, split);
    return strings;
  }

  protected void next(BeforeEvent event, Class<? extends Component> variant) {
    event.rerouteTo(variant);
  }

  protected <T> void next(BeforeEvent event, String nextLocation,
      List<T> nextParams) {
    event.rerouteTo(nextLocation, nextParams);
  }
}
