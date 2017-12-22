package de.wathoserver.visjs.visjs_example;

import javax.servlet.annotation.WebServlet;

import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Panel;
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

  private NetworkDiagram nd;

  @Override
  protected void init(final VaadinRequest vaadinRequest) {



    final Options options = new Options();
    options.setHeight("100%");
    options.setWidth("100%");
    nd = new NetworkDiagram(options);
    nd.setSizeFull();
    populateGraph();

    final Panel content = new Panel("VisJs-Example", nd);
    content.setHeight("400px");
    content.setWidth("400px");
    setContent(content);
  }

  private void populateGraph() {
    nd.addNode(new Node("1", "Node 1"), new Node("2", "Node 2"), new Node("3", "Node 3"));
    nd.addEdge(new Edge("1", "2"), new Edge("1", "3"));
    nd.addEdge(new Edge("2", "3"));
  }

  @WebServlet(urlPatterns = "/*", name = "VisJsExampleUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = VisJsExampleUI.class, productionMode = false)
  public static class VisJsExampleUIServlet extends VaadinServlet {
  }
}
