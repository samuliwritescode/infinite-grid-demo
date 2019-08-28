package org.vaadin.samuli;

import com.vaadin.flow.server.VaadinServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "infiniteservlet", asyncSupported = false,
    initParams = {
      @WebInitParam(name = "compatibilityMode", value = "true")
    })
public class InfiniteServlet extends VaadinServlet {
  public InfiniteServlet() {
    super();
    System.out.println("init infiniteservlet");
  }
}
