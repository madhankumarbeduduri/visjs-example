package de.wathoserver.visjs.visjs_example;

import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SimpleExampleView extends VerticalLayout implements View {

  private NetworkDiagram nd;

  @Override
  public void enter(final ViewChangeEvent event) {
    removeAllComponents();
    final Options options = new Options();
    options.setHeight("100%");
    options.setWidth("100%");
    options.setAutoResize(true);
    nd = new NetworkDiagram(options);
    nd.setSizeFull();
    populateGraph();

    final Panel content = new Panel("VisJs-SimpleExample", nd);
    content.setHeight("400px");
    content.setWidth("400px");
    addComponent(content);
  }

  private void populateGraph() {
    nd.addNode(new Node("1", "Node 1"), new Node("2", "Node 2"), new Node("3", "Node 3"));
    nd.addEdge(new Edge("1", "2"), new Edge("1", "3"));
    nd.addEdge(new Edge("2", "3"));
  }
}
