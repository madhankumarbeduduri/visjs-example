package de.wathoserver.visjs.visjs_example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class MoveToExampleView extends HorizontalLayout implements View {

  private NetworkDiagram nd;
  private final NumberFormat df = DecimalFormat.getInstance();
  private Button scaleBtn;

  @Override
  public void enter(final ViewChangeEvent event) {
    removeAllComponents();
    final Options options = new Options();
    options.setHeight("100%");
    options.setWidth("100%");
    options.setAutoResize(true);
    options.getInteraction().setNavigationButtons(false);
    nd = new NetworkDiagram(options);
    nd.setSizeFull();
    populateGraph();

    final Panel content = new Panel("VisJs-MoveToExample", nd);
    content.setHeight("400px");
    content.setWidth("400px");
    addComponent(content);

    final GridLayout gridLayout = createButtons();
    addComponent(gridLayout);
  }

  private GridLayout createButtons() {
    final GridLayout gridLayout = new GridLayout(2, 3);
    gridLayout.setMargin(true);
    gridLayout.setSpacing(true);
    gridLayout.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
    // textfield and button to scale
    final TextField scale = new TextField("Scale");
    scale.setPlaceholder("Enter zoom factor");
    scale.addValueChangeListener(e -> {
      try {
        final Number number = df.parse(e.getValue());
        scaleBtn.setEnabled(true);
        scaleBtn.setCaption("Scale to " + number);
        scale.setComponentError(null);
      } catch (final ParseException e1) {
        scaleBtn.setEnabled(false);
        scaleBtn.setCaption("NaN");
        scale.setComponentError(new UserError("Not a number"));
      }
    });
    gridLayout.addComponent(scale);
    scaleBtn = new Button("Scale");
    scaleBtn.setWidth(100, Unit.PERCENTAGE);
    scaleBtn.setEnabled(false);
    scaleBtn.addClickListener(e -> {
      try {
        nd.moveTo(df.parse(scale.getValue()));
      } catch (final ParseException e1) {
      }
    });
    // Button to fit
    gridLayout.addComponent(scaleBtn);
    final Button fitBtn = new Button("Fit");
    fitBtn.setWidth(12, Unit.EM);
    fitBtn.addClickListener(e -> nd.fit());
    gridLayout.addComponent(fitBtn, 1, 1);
    // generic moveTo
    final Button moveToBtn = new Button("moveToAbsolute");
    moveToBtn.setWidth(100, Unit.PERCENTAGE);
    moveToBtn.addClickListener(e -> {
      nd.moveTo(20, 40, 1, 0, 0);
    });
    gridLayout.addComponent(moveToBtn, 0, 2);
    final Button moveToOffsetBtn = new Button("moveToOffset");
    moveToOffsetBtn.setWidth(12, Unit.EM);
    moveToOffsetBtn.addClickListener(e -> {
      nd.moveTo(0, 0, 1, -40, -20);
    });
    gridLayout.addComponent(moveToOffsetBtn, 1, 2);


    return gridLayout;
  }

  private void populateGraph() {
    nd.addNode(new Node("1", "Node 1"), new Node("2", "Node 2"), new Node("3", "Node 3"));
    nd.addEdge(new Edge("1", "2"), new Edge("1", "3"));
    nd.addEdge(new Edge("2", "3"));
  }
}
