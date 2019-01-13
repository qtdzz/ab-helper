package com.qtdzz.abhelper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;

public class ABController {

    public static void setABVariant(Component component, ABOptions options) {
        switch (options.getType()) {
            case CLASS:
                setABVariantClass(component, options);
                break;
            case THEME:
                setABVariantTheme(component, options);
                break;
            default:
                throw new IllegalStateException("Error");
        }
    }

    private static void setABVariantClass(Component component, ABOptions options) {
        ABStrategy strategy = new ABStrategy();
        if (!(component instanceof HasStyle)) {
            String message = String.format("Can't set %s variants for non %s components", options.getType(), HasStyle.class.getName());
            throw new IllegalStateException(message);
        }
        int variant = strategy.getVariant(options.getAb().length);
        ((HasStyle) component).addClassName(options.getAb()[variant]);
    }

    private static void setABVariantTheme(Component component, ABOptions options) {
        ABStrategy strategy = new ABStrategy();
        if (!(component instanceof HasTheme)) {
            String message = String.format("Can't set %s variants for non %s components", options.getType(), HasTheme.class.getName());
            throw new IllegalStateException(message);
        }
        int variant = strategy.getVariant(options.getAb().length);
        ((HasTheme) component).addThemeName(options.getAb()[variant]);
    }
}
