package de.wathoserver.visjs.visjs_example;

import java.util.concurrent.ThreadLocalRandom;

import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;
import org.vaadin.visjs.networkDiagram.options.edges.Layout;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SimpleFullscreenExampleView extends VerticalLayout implements View {

  private NetworkDiagram nd;

  public SimpleFullscreenExampleView() {
    super();
    setSizeFull();
  }

  @Override
  public void enter(final ViewChangeEvent event) {
    removeAllComponents();
    final Options options = new Options();
    options.setHeight("100%");
    options.setWidth("100%");
    options.setAutoResize(true);
    final Layout layout = new Layout();
    layout.setImprovedLayout(true);
    options.setLayout(layout);
    nd = new NetworkDiagram(options);
    nd.setSizeFull();
    nd.addStabilizedListener(networkEvent -> nd.fit());
    final Panel content = new Panel("VisJs-SimpleExample", nd);
    content.setSizeFull();
    addComponent(content);
    populateGraph();
  }

  private void populateGraph() {
    final int maxNodes = 50;
    for (int i = 0; i < maxNodes; i++) {
      nd.addNode(new Node(i, "Node " + i));
    }
    for (int i = 0; i < 150; i++) {
      nd.addEdge(new Edge(ThreadLocalRandom.current().nextInt(0, maxNodes + 1),
          ThreadLocalRandom.current().nextInt(0, maxNodes + 1)));
    }
  }
}
