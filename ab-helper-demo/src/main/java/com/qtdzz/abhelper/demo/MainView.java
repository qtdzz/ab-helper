package com.qtdzz.abhelper.demo;

import com.qtdzz.abhelper.ABController;
import com.qtdzz.abhelper.ABOptions;
import com.qtdzz.abhelper.ABVariants;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {
    @ABVariants
    private Button mybutton;

    public MainView() {
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        mybutton = new Button("ping ping");
        add(button);
        add(mybutton);
        ABOptions options = new ABOptions(ABOptions.ABType.THEME, "primary", "tertiary");
        ABController.setABVariant(mybutton, options);
    }
}