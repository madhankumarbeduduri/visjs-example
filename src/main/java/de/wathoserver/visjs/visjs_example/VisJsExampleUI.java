package de.wathoserver.visjs.visjs_example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or
 * some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("visjs-theme")
public class VisJsExampleUI extends UI {

  public static final String VIEW_START = "";
  public static final String VIEW_SIMPLE_EXAMPLE = "simple_example";
  public static final String VIEW_SIMPLE_FULLSCREEN_EXAMPLE = "simple_fullscreen_example";
  public static final String VIEW_MOVETO_EXAMPLE = "moveTo_example";

  private Navigator navigator;


  @Override
  protected void init(final VaadinRequest vaadinRequest) {
    getPage().setTitle("VisJs-Example");
    navigator = new Navigator(this, this);
    navigator.addView(VIEW_START, new StartView());
    navigator.addView(VIEW_SIMPLE_EXAMPLE, new SimpleExampleView());
    navigator.addView(VIEW_SIMPLE_FULLSCREEN_EXAMPLE, new SimpleFullscreenExampleView());
    navigator.addView(VIEW_MOVETO_EXAMPLE, new MoveToExampleView());
  }


  @WebServlet(urlPatterns = "/*", name = "VisJsExampleUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = VisJsExampleUI.class, productionMode = false)
  public static class VisJsExampleUIServlet extends VaadinServlet {
  }
}
