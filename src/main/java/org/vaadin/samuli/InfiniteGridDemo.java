package org.vaadin.samuli;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@JsModule("./src/shared-styles.js")
@JsModule("./src/colorful-cell.js")
public class InfiniteGridDemo extends VerticalLayout {

  public InfiniteGridDemo() {
    HorizontalLayout firstRow = new HorizontalLayout();
    HorizontalLayout secondRow = new HorizontalLayout();
    firstRow.setWidth("100%");
    secondRow.setWidth("100%");
    firstRow.setSpacing(false);
    secondRow.setSpacing(false);
    firstRow.setMargin(false);
    secondRow.setMargin(false);

    InfiniteGrid textGrid = createInfiniteGrid();
    textGrid.setFrozenRows(1);
    textGrid.setFrozenColumns(1);
    textGrid.setHtmlGenerator((x,y)-> {
      if (x == 0 && y == 0) {
        return "";
      }

      if (x == 0) {
        return "row "+y;
      }

      if (y == 0) {
        return "col "+x;
      }

      return String.format("%d, %d", x,y);
    }, InfiniteGrid.HTMLRenderingHints.TEXT_ONLY);
    firstRow.add(textGrid);

    InfiniteGrid htmlGrid = createInfiniteGrid();
    htmlGrid.setHtmlGenerator((x, y) -> {
      if (y%2==1) {
        return String.format("%d, %d", x,y);
      }

      return String.format("<div style='height:100%%;width:100%%;background-color: #eee'>%d, %d</div>", x, y);
    }, InfiniteGrid.HTMLRenderingHints.NORMAL);
    firstRow.add(htmlGrid);

    InfiniteGrid componentGrid = createInfiniteGrid();
    componentGrid.setComponentGenerator((x, y) ->
        new Button(
            String.format("%d, %d", x, y),
            e -> Notification.show(String.format("clicked (%d, %d)", x, y))
        ));
    secondRow.add(componentGrid);

    InfiniteGrid colorGrid = new InfiniteGrid();
    colorGrid.setCellSize(70,70);
    colorGrid.setItemCount(1000, 1000);
    colorGrid.setTemplateGenerator("<colorful-cell></colorful-cell>");
    colorGrid.getElement()
        .addEventListener("clickcell", e -> Notification.show("Clicked " +
            (int)e.getEventData().getObject("event.detail").getNumber("x") + ", " +
            (int)e.getEventData().getObject("event.detail").getNumber("y")))
        .addEventData("event.detail");
    secondRow.add(colorGrid);

    firstRow.setFlexGrow(0.5, textGrid);
    firstRow.setFlexGrow(0.5, htmlGrid);
    secondRow.setFlexGrow(0.5, componentGrid);
    secondRow.setFlexGrow(0.5, colorGrid);
    setSizeFull();
    setSpacing(true);
    setMargin(false);
    setPadding(false);
    H3 title = new H3(
        "Below there are 4 InfiniteGrids. 1. Server generated text with column and row header. 2. Server generated html. 3. Vaadin components. 4. Static lit template."
    );
    add(
        title,
        firstRow,
        secondRow
    );
    setFlexGrow(0, title);
    setFlexGrow(0.5, firstRow);
    setFlexGrow(0.5, secondRow);
  }

  private InfiniteGrid createInfiniteGrid() {
    InfiniteGrid infiniteGrid = new InfiniteGrid();
    infiniteGrid.getElement().getClassList().add("borders");
    infiniteGrid.setCellSize(200, 40);
    infiniteGrid.setItemCount(100, 100);
    return infiniteGrid;
  }
}
