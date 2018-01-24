package de.wathoserver.visjs.visjs_example;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class StartView extends VerticalLayout implements View {

  public static final String VIEWNAME = "";

  public StartView() {
    addComponent(new Button("Simple example", event -> {
      getUI().getNavigator().navigateTo(VisJsExampleUI.VIEW_SIMPLE_EXAMPLE);
    }));
    addComponent(new Button("Simple fullscreen example", event -> {
      getUI().getNavigator().navigateTo(VisJsExampleUI.VIEW_SIMPLE_FULLSCREEN_EXAMPLE);
    }));
    addComponent(new Button("MoveTo example", event -> {
      getUI().getNavigator().navigateTo(VisJsExampleUI.VIEW_MOVETO_EXAMPLE);
    }));
  }
}
